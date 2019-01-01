package zy.dao;


import zy.entity.OrderBook;
import java.util.ArrayList;
public interface OrderBookDao {


     void addOrderBook(int useID, int biid);





     ArrayList<OrderBook> queryAllOrderBook();






}
