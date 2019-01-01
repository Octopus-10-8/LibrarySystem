package zy.bizImpl;

import zy.biz.UserBiz;
import zy.dao.RecordDao;
import zy.dao.UserDao;
import zy.daoImpl.RecordDaoImpl;
import zy.daoImpl.UserDaoImpl;
import zy.entity.Records;
import zy.entity.Users;
import zy.utils.Utils;

import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class UserBizImpl implements UserBiz {


    private UserDao userDao = new UserDaoImpl();
    private RecordDao recordDao = new RecordDaoImpl();

    @Override
    public boolean login(String name, String password) {
        Users users = new Users(name, password);
        Users byName = userDao.findByName(users.getName());
        if (byName == null)
            return false;
        if (byName.getName().equals(users.getName()) && byName.getPasssword().equals(users.getPasssword())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean regist(Users users) {
        if (userDao.findByName(users.getName()) == null) {   //如果没有账号的元素的话
            userDao.save(users);
            return true;
        }
        return false;
    }

    @Override
    public Users findByName(String name) {

        return userDao.findByName(name);
    }


    @Override
    public boolean isReturnBook(int userid) {

        ArrayList<Records> records = recordDao.queryAllRecords();
        for (Records record : records) {
            if (record.getUid() == userid) {
                if (record.getShouldReturnTime().compareTo(Utils.getTime()) == 0) {
                    return true;
                }

            }
        }
        return false;


    }

    @Override
    public boolean renewMenu(int userid, int recordID) {

        ArrayList<Records> records = recordDao.queryAllRecords();
        for (Records record : records) {
            if (record.getId() == recordID && record.getUid() == userid) {
                //判断是否是会员
                if (userDao.findById(userid).getLevel() == 1) {
                    record.setShouldReturnTime(Utils.updateDate(record.getShouldReturnTime(), 14));
                } else {
                    record.setShouldReturnTime(Utils.updateDate(record.getShouldReturnTime(), 7));

                }
                recordDao.updateRecord(record);
                return true;
            }

        }
        return false;
    }


    @Override
    public ArrayList<Users> queryAllUser() {
        return userDao.queryAllUser();
    }


    @Override
    public void update(Users users) {
        userDao.update(users);
    }
}
