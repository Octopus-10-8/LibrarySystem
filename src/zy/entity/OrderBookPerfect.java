package zy.entity;

/**
 * Date: 2018/12/22 0022
 **/
public class OrderBookPerfect {

    private Users users;
    private Bookinfo bookinfo;

    public OrderBookPerfect(Users users, Bookinfo bookinfo) {
        this.users = users;
        this.bookinfo = bookinfo;
    }


    @Override
    public String toString() {
        return "预约信息{" +"用户编号=["+users.getId()+"]\t用户账号名=["+users.getName()+"]\t用户指数=["+users.getPoint()+"]\t等级["+users.getLevel()+"]\t图书上架编号=["+bookinfo.getId()+"]"+
                "}";
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }
}
