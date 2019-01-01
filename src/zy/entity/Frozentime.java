package zy.entity;

import java.io.Serializable;

/**
 * frozentime	帐号冻结表	冻结表ID	用户ID	冻结时间	解冻时间
 * id	uid	frozentime	unfrozentime
 * 冻结表
 * Date: 2018/12/22 0022
 **/
public class Frozentime implements Serializable {

    private int id;
    private int userId;
    private String frozentime;
    private String unfrozentime;

    public Frozentime(int userId, String frozentime, String unfrozentime) {
        this.userId = userId;
        this.frozentime = frozentime;
        this.unfrozentime = unfrozentime;
    }
    public String getUnfrozentime() {
        return unfrozentime;
    }

    public void setUnfrozentime(String unfrozentime) {
        this.unfrozentime = unfrozentime;
    }

    @Override
    public String toString() {
        return "Frozentime{" +
                "id=" + id +
                ", userId=" + userId +
                ", frozentime='" + frozentime + '\'' +
                ", unfrozentime='" + unfrozentime + '\'' +
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

    public String getFrozentime() {
        return frozentime;
    }

    public void setFrozentime(String frozentime) {
        this.frozentime = frozentime;
    }

    public Frozentime(int id, int userId, String frozentime) {
        this.id = id;
        this.userId = userId;
        this.frozentime = frozentime;
    }
}
