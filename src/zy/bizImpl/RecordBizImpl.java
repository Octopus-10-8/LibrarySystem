package zy.bizImpl;

import com.sun.prism.impl.Disposer;
import zy.biz.RecordBiz;
import zy.dao.*;
import zy.daoImpl.*;
import zy.entity.*;
import zy.utils.Utils;

import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class RecordBizImpl implements RecordBiz {

    private BookinfoDao bookinfoDao = new BookInfoImpl();
    private RecordDao recordDao = new RecordDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BooksDao booksDao = new BooksDaoIml();
    private FrozentimeDao frozentimeDao=new FrozentimeDaoImpl();

    @Override
    public boolean addRecords(Records records) {
        Bookinfo bookinfo = bookinfoDao.queryOneBookInfo(records.getBookinfoid());
        if (bookinfo == null) {

            return false;
        }
        if (bookinfo.getInorout().equals("在馆") && bookinfo.getState().equals("未损坏") && bookinfo.getLost().equals("未丢失")) {
            recordDao.save(records);
            //借阅成功的话，有三个东西需要变动已借出数量、被借出数量、在馆状态
            //修改在馆状态
            bookinfoDao.updateBookInfoInorout(bookinfo.getId(), "离馆");
            Books books = booksDao.booksQueryById(bookinfo.getBid());
            books.setDiscount(books.getDiscount() + 1);
            books.setHasLended(books.getHasLended() + 1);
            booksDao.booksUpdate(books);
            return true;
        }

        return false;
    }


    /**
     * 查询所有记录信息（显示图书信息和用户信息  管理员的权限）
     *
     * @return
     */
    @Override
    public ArrayList<RecordsPerfect> queryAllRecords() {
        ArrayList<Records> records = recordDao.queryAllRecords();
        ArrayList<RecordsPerfect> recordsPerfects = new ArrayList<>();
        for (Records record : records) {
            Users byId = userDao.findById(record.getUid());
            Bookinfo bookinfo = bookinfoDao.queryOneBookInfo(record.getBookinfoid());
            RecordsPerfect rp = new RecordsPerfect(record, byId, bookinfo);
            recordsPerfects.add(rp);
        }
        return recordsPerfects;
    }

    /**
     * 查询自己的借阅信息（用户权限）
     */
    public ArrayList<RecordsPerfect> queryUserRecords(Users users) {
        ArrayList<RecordsPerfect> recordsPerfects = queryAllRecords();
        ArrayList<RecordsPerfect> recordsUser = new ArrayList<>();
        for (RecordsPerfect recordsPerfect : recordsPerfects) {
            if (recordsPerfect.getUsers().getName().equals(users.getName())) {   //用户账号唯一的
                recordsUser.add(recordsPerfect);

            }

        }
        return recordsUser;

    }

    public boolean returnBook(int recordId) {
        ArrayList<Records> records = recordDao.queryAllRecords();
        //1:先判断biid是否存在
        boolean flag = false;
        for (Records record : records) {
            if (record.getId() == recordId) {
                flag = true;
            }
        }
        if (!flag)
            return false;
        //判断这本书是否是已经借出，并且未归还的状态
        // 因为这本书可能历史上该用户已经结过并且已经归还
        ArrayList<RecordsPerfect> recordsPerfects = queryAllRecords();
        for (RecordsPerfect recordsPerfect : recordsPerfects) {
            if (recordsPerfect.getRecord().getId() == recordId) {
                if (!recordsPerfect.getRecord().getReturnTime().equals("未归还")) {
                    return false;
                }
                if (recordsPerfect.getBookinfo().getInorout().equals("在馆")) {

                    return false;
                }
                //检查状态
                if (recordsPerfect.getBookinfo().getLost().equals("丢失")) {
                    Users users = recordsPerfect.getUsers();
                    users.setPoint(users.getPoint() - 20);
                    userDao.update(users);

                    System.out.println(users.getPoint());
                    if (users.getPoint()<0){
                        //添加数据到冻结表中
                        Frozentime frozentime=new Frozentime(users.getId(),"冻结","未解冻");
                        frozentimeDao.saveFrozen(frozentime);
                        System.out.println(frozentime);

                    }
                    return false;
                }
                if (recordsPerfect.getBookinfo().getState().equals("损坏")) {
                    Users users = recordsPerfect.getUsers();
                    users.setPoint(users.getPoint() - 10);
                    userDao.update(users);
                    if (users.getPoint()<0){
                        //添加数据到冻结表中
                        Frozentime frozentime=new Frozentime(users.getId(),"冻结","未解冻");
                        frozentimeDao.saveFrozen(frozentime);
                    }
                    return false;
                }
                //如果都不满足的话，则正常归还,填写已归还时间  ,更新已借出数量减一
                //正常还书的话积分加五分
                //此外，注意判断超时的话根据经验来的话也是正常还书的只是会扣分
                if (recordsPerfect.getRecord().getShouldReturnTime().compareTo(Utils.getTime())>0){
                    Users users = recordsPerfect.getUsers();
                    users.setPoint(users.getPoint() -5);
                    userDao.update(users);
                }
                Users users = recordsPerfect.getUsers();
                users.setPoint(users.getPoint()+5);
                //在这里做最后的判断，看用户的积分是否低于0，如果是更新状态
                if (users.getPoint()<0){
                    //添加数据到冻结表中
                    Frozentime frozentime=new Frozentime(users.getId(),"冻结","未解冻");
                    frozentimeDao.saveFrozen(frozentime);
                }
                userDao.update(users);
                 //修改离馆为在馆状态
                Records r = recordsPerfect.getRecord();
                bookinfoDao.updateBookInfoInorout(r.getBookinfoid(),"在馆");
                r.setReturnTime(Utils.getTime());
                recordDao.updateRecord(r);
                Books books = booksDao.booksQueryById(recordsPerfect.getBookinfo().getBid());
                books.setHasLended(books.getHasLended() - 1);
                booksDao.booksUpdate(books);
                return true;

            }
        }
        // 该书是否丢失，是否损坏，如果有以上情况，需要扣除用户积分，并且修改这本书的状态。
        return false;
    }


    @Override
    public Records queryById(int recordsID) {

        return recordDao.queryById(recordsID);
    }
}
