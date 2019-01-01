package zy.daoImpl;

import zy.dao.RecordDao;
import zy.dao.UserDao;
import zy.entity.Records;
import zy.entity.Users;
import zy.utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class UserDaoImpl extends BaseDao implements UserDao {
    ArrayList<Users> readList = null;

    public UserDaoImpl() {
        super(new File("user.txt"));
    }




    @Override
    public void save(Users user) {
        readList = read();
        System.out.println(readList);
        System.out.println(user);
        if (readList.size() == 0) {
            user.setId(1);

        } else {
            user.setId(readList.get(readList.size() - 1).getId() + 1);

        }
        readList.add(user);
        write(readList);
        closeAll();
    }

    @Override
    public Users findByName(String name) {
        readList = read();
        for (Users u : readList) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public Users findById(int id) {
        readList = read();
        for (Users u : readList) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Users> queryAllUser() {
        ArrayList<Users> read = read();
        return read;
    }

    /**
     * 更新用户信息
     *
     * @param users
     */
    @Override
    public void update(Users users) {
        ArrayList<Users> read = read();
        for (int i = 0; i < read.size(); i++) {
            if (read.get(i).getId() == users.getId()) {
                read.set(i, users);
                write(read);
                closeAll();

            }
        }
    }


}
