package zy.entity;

import java.io.Serializable;

/**
 * 图书馆图书信息表（从表）图书馆书本ID	书表ID	是否在图书馆	是否损坏	是否丢失
 * id	bid	inorout（1在）	state	lost
 *
 *
 **/
public class Bookinfo implements Serializable {

    private int id;   //图书馆书本ID
    private int bid;  //书表ID
    private String inorout="在馆"; //是否在图书馆  1在
    private String state="未损坏";//是否损坏   1否
    private String lost="未丢失";//是否丢失    1否


    public Bookinfo(int bid) {
        this.bid = bid;
    }


    @Override
    public String toString() {
        return "Bookinfo{" +
                "图书编号=" + id +
                ", 书籍号=" + bid +
                ", 是否在图书馆=" + inorout +
                ", 是否损坏=" + state +
                ", 是否丢失=" + lost +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getInorout() {
        return inorout;
    }

    public void setInorout(String inorout) {
        this.inorout = inorout;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }


}
