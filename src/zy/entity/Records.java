package zy.entity;

import java.io.Serializable;

/**
 * Date: 2018/12/21 0021
 * <p>
 * <p>
 * records	图书借出记录表	记录表ID	用户ID	图书馆书本ID	借出时间	归还时间
 * id	uid	biid	lendTime	returnTime
 * 1	3	4	2016	2016.4.25
 **/
public class Records implements Serializable {
    private int id;
    private int uid;
    private int bookinfoid;
    private String lendTime = "未借出";
    private String returnTime = "未归还";
    private String ShouldReturnTime;    //应还日期


    public String getShouldReturnTime() {
        return ShouldReturnTime;
    }

    public void setShouldReturnTime(String shouldReturnTime) {
        ShouldReturnTime = shouldReturnTime;
    }

    public Records(int uid, int bookinfoid, String lendTime, String shouldReturnTime) {
        this.uid = uid;
        this.bookinfoid = bookinfoid;
        this.lendTime = lendTime;
        ShouldReturnTime = shouldReturnTime;
    }

    public Records(int id, int uid, int bookinfoid, String lendTime, String returnTime) {
        super();
        this.id = id;
        this.uid = uid;
        this.bookinfoid = bookinfoid;
        this.lendTime = lendTime;
        this.returnTime = returnTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBookinfoid() {
        return bookinfoid;
    }

    public void setBookinfoid(int bookinfoid) {
        this.bookinfoid = bookinfoid;
    }

    public String getLendTime() {
        return lendTime;
    }

    public void setLendTime(String lendTime) {
        this.lendTime = lendTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return "Records{" +
                "id=" + id +
                ", uid=" + uid +
                ", bookinfoid=" + bookinfoid +
                ", lendTime='" + lendTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", ShouldReturnTime='" + ShouldReturnTime + '\'' +
                '}';
    }
}
