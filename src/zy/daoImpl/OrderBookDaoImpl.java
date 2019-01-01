package zy.daoImpl;

import zy.dao.OrderBookDao;
import zy.entity.Bookinfo;
import zy.entity.OrderBook;
import zy.entity.Users;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class OrderBookDaoImpl extends BaseDao implements OrderBookDao {

    public OrderBookDaoImpl() {
        super(new File("orderBook.txt"));
    }

    private ArrayList<OrderBook> orderBooks;

    @Override
    public void addOrderBook(int users, int biid) {
        orderBooks = read();
        OrderBook orBook = new OrderBook(users, biid);
        if (orderBooks.size() == 0) {
            orBook.setId(1);
        } else {
            orBook.setId(orderBooks.get(orderBooks.size() - 1).getId() + 1);
        }
        orderBooks.add(orBook);
        write(orderBooks);
        closeAll();
    }



    @Override
    public ArrayList<OrderBook> queryAllOrderBook() {
        orderBooks = read();

        return orderBooks;
    }

}
