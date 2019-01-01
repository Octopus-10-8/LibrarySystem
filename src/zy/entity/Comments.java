package zy.entity;

import java.io.Serializable;

/**
 * Date: 2018/12/22 0022
 **/
public class Comments implements Serializable {
    private int id;
    private int userID;   //用户信息
    private int biid;  //上架图书信息

    private String comments;   //评论内容



    @Override
    public String toString() {
        return "评论->{" +
                "id=" + id +
                ", 用户编号=" + userID +
                ", 上架图书编号=" + biid +
                ", 评论内容='" + comments + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBiid() {
        return biid;
    }

    public void setBiid(int biid) {
        this.biid = biid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Comments(int id, int userID, int biid, String comments) {
        this.id = id;
        this.userID = userID;
        this.biid = biid;
        this.comments = comments;
    }

    public Comments(int userID, int biid, String comments) {
        this.userID = userID;
        this.biid = biid;
        this.comments = comments;
    }
}
