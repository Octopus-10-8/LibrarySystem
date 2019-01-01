package zy.entity;

/**
 * 用户冻结判断
 * Date: 2018/12/24 0024
 **/
public class FrozentimeJudge {

    private Frozentime frozentime;
    private Users users;
    private Records records;
    private Bookinfo bookinfo;
    public FrozentimeJudge(Frozentime frozentime, Users users, Records records, Bookinfo bookinfo) {
        this.frozentime = frozentime;
        this.users = users;
        this.records = records;
        this.bookinfo = bookinfo;
    }


    public Frozentime getFrozentime() {
        return frozentime;
    }

    public void setFrozentime(Frozentime frozentime) {
        this.frozentime = frozentime;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }

    @Override
    public String toString() {
        return "FrozentimeJudge{" +
                "frozentime=" + frozentime +
                ", users=" + users +
                ", records=" + records +
                ", bookinfo=" + bookinfo +
                '}';
    }
}
