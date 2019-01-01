package zy.biz;


import zy.entity.OrderBookPerfect;

import java.util.ArrayList;

public interface OrderBookBiz {

    boolean addOrderBook(int useID, int biid);


    ArrayList<OrderBookPerfect> queryAllOrderBook();

    ArrayList<OrderBookPerfect> queryOrderBookByUser(int userID);

}
