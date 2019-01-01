package zy.bizImpl;

import zy.biz.BookInfoBiz;
import zy.biz.OrderBookBiz;
import zy.dao.BookinfoDao;
import zy.dao.OrderBookDao;
import zy.dao.UserDao;
import zy.daoImpl.BookInfoImpl;
import zy.daoImpl.OrderBookDaoImpl;
import zy.daoImpl.UserDaoImpl;
import zy.entity.Bookinfo;
import zy.entity.OrderBook;
import zy.entity.OrderBookPerfect;
import zy.entity.Users;

import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class OrderBookBizImpl implements OrderBookBiz {


    private BookinfoDao bookinfoDao = new BookInfoImpl();
    private OrderBookDao orderBookDao = new OrderBookDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean addOrderBook(int userID, int biid) {
        //先查询biid是否存在，检查状态是否在馆
        Bookinfo bookinfo = bookinfoDao.queryOneBookInfo(biid);
        if (bookinfo == null) {
            return false;
        }
        orderBookDao.addOrderBook(userID, biid);
        return true;
    }

    @Override
    public ArrayList<OrderBookPerfect> queryAllOrderBook() {
        ArrayList<OrderBook> o = orderBookDao.queryAllOrderBook();
        ArrayList<OrderBookPerfect> orderBookPerfects = new ArrayList<>();
        for (OrderBook orderBook : o) {
            Users u = userDao.findById(orderBook.getUserId());
            Bookinfo b = bookinfoDao.queryOneBookInfo(orderBook.getBiid());
            OrderBookPerfect obp = new OrderBookPerfect(u, b);
            orderBookPerfects.add(obp);
        }
        return orderBookPerfects;
    }

    @Override
    public ArrayList<OrderBookPerfect> queryOrderBookByUser(int userID) {
        //判断用户ID是否存在
        ArrayList<Users> users = userDao.queryAllUser();
        ArrayList<OrderBookPerfect> arrayList = new ArrayList<>();
        boolean flag = false;
        for (Users user : users) {
            if (user.getId() == userID) {
                flag = true;
            }
        }
        if (flag) {

            ArrayList<OrderBookPerfect> orderBookPerfects = queryAllOrderBook();
            for (OrderBookPerfect orderBookPerfect : orderBookPerfects) {
                if (orderBookPerfect.getUsers().getId() == userID) {
                    arrayList.add(orderBookPerfect);
                }
            }
            return arrayList;
        } else {
            return null;
        }

    }
}
