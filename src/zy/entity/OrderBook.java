package zy.entity;

import java.io.Serializable;

/**
 * book	预约图书表：	预约图书表ID	用户ID	图书表ID
 * id	uid	bid(我写的biid)
 * <p>
 * Date: 2018/12/22 0022
 **/
public class OrderBook  implements Serializable {

    private int id;
    private int userId;
    private int biid;

    public OrderBook(int id, int userId, int biid) {
        this.id = id;
        this.userId = userId;
        this.biid = biid;
    }

    public OrderBook(int userId, int biid) {
        this.userId = userId;
        this.biid = biid;
    }





    @Override
    public String toString() {
        return "OrderBook{" +
                "id=" + id +
                ", userId=" + userId +
                ", biid=" + biid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBiid() {
        return biid;
    }

    public void setBiid(int biid) {
        this.biid = biid;
    }
}
