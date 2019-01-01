package zy.entity;

/**
 * Date: 2018/12/22 0022
 **/
public class RecordsPerfect {
    private Records record;
    private Users users;
    private Bookinfo bookinfo;

    @Override
    public String toString() {
        return "借阅信息{" +"记录ID=["+record.getId()+
                "]\t用户名=[" + users.getName() + "]\t用户等级=[" + users.getLevel() + "]\t用户指数=[" + users.getPoint() +
                "]\t图书编号=[" +bookinfo.getId()+"]\t书籍号=["+bookinfo.getBid()+"]\t在馆状态=["+bookinfo.getInorout()+
                "]\t损坏状态=["+bookinfo.getState()+"]\n\t\t丢失状态=["+bookinfo.getLost()+"]\t借出时间=["+record.getLendTime()+"]\t应还日期=["+record.getShouldReturnTime()+"]\t归还时间=["+record.getReturnTime()+
                "]}";
    }

    public Records getRecord() {
        return record;
    }

    public void setRecord(Records record) {
        this.record = record;
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

    public RecordsPerfect(Records record, Users users, Bookinfo bookinfo) {
        this.record = record;
        this.users = users;
        this.bookinfo = bookinfo;
    }
}
