package zy.entity;

/**
 * Date: 2018/12/22 0022
 * <p>
 * <p>
 * private int id;
 * private int userId;
 * private String frozentime;
 * private String unfrozentime;
 **/
public class FrozentimePerfect {

    private Frozentime frozentime;
    private Users users;

    public FrozentimePerfect(Frozentime frozentime, Users users) {
        this.frozentime = frozentime;
        this.users = users;

    }




    @Override
    public String toString() {
        return "冻结信息{" +
                "冻结ID=[" + frozentime.getId() +"]\t用户ID=["+frozentime.getUserId()+"]\t用户账号=["+users.getName()+
                "]\t冻结状态"+frozentime.getFrozentime()+"]\t未解冻状态=["+frozentime.getUnfrozentime()+"]\t用户指数=["+users.getPoint()
                +"]\t用户等级=["+users.getLevel()+"]}";

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
}
