package zy.entity;

import java.io.Serializable;

/**
 * 用户实体类id	name	password	point	level
 **/
public class Users  implements Serializable {
    private int id;
    private String name;
    private String passsword;
    private int point;
    private int level;    //普通用户  0  ，VIP   1


    public Users(String name, String passsword) {
        this.name = name;
        this.passsword = passsword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Users(String name, String passsword, int point, int level) {
        this.name = name;
        this.passsword = passsword;
        this.point = point;
        this.level = level;
    }

    public Users(int id, String name, String passsword, int point, int level) {
        this.id = id;
        this.name = name;
        this.passsword = passsword;
        this.point = point;
        this.level = level;
    }

    @Override
    public String toString() {
        return "用户{" +
                "用户id=[" + id +
                "] \t用户姓名=['" + name + '\'' +
                "] \t用户密码='[" + passsword + '\'' +
                "] \t用户指数=[" + point +
                "] \t用户等级=[" + level +
                "]}";
    }
}
