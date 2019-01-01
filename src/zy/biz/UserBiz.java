package zy.biz;

import zy.entity.Users;

import java.util.ArrayList;

public interface UserBiz {
    boolean login(String name, String password);

    boolean regist(Users users);

    Users findByName(String name);

    boolean isReturnBook(int userid);

    boolean renewMenu(int userid, int biid);

    ArrayList<Users> queryAllUser();


    void update(Users users);

}
