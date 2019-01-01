package zy.daoImpl;

import zy.dao.FrozentimeDao;
import zy.entity.Bookinfo;
import zy.entity.Frozentime;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class FrozentimeDaoImpl extends BaseDao implements FrozentimeDao {

    public FrozentimeDaoImpl() {
        super(new File("frozentime.txt"));
    }

    @Override
    public void saveFrozen(Frozentime frozentime) {

        ArrayList<Frozentime> read = read();
        if (read.size() == 0) {
            frozentime.setId(1);
        } else {
            frozentime.setId(read.get(read.size() - 1).getId() + 1);
        }
        read.add(frozentime);
        write(read);
        closeAll();


    }

    @Override
    public ArrayList<Frozentime> queryFrozentime() {
        ArrayList<Frozentime> read = read();
        return read;
    }


    /**
     * @param id 为冻结表主键ID
     */
    @Override
    public void updateUserState(int id, Frozentime frozentime) {
        ArrayList<Frozentime> read = read();
        for (int i = 0; i < read.size(); i++) {
            if (read.get(i).getId()==id){
             read.set(i,frozentime);
             write(read);
             closeAll();
            }

        }

    }
}
