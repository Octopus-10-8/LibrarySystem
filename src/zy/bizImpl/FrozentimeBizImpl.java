package zy.bizImpl;

import zy.biz.FrozentimeBiz;
import zy.dao.FrozentimeDao;
import zy.dao.UserDao;
import zy.daoImpl.FrozentimeDaoImpl;
import zy.daoImpl.UserDaoImpl;
import zy.entity.Frozentime;
import zy.entity.FrozentimePerfect;
import zy.entity.Users;

import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class FrozentimeBizImpl implements FrozentimeBiz {
    private FrozentimeDao frozentimeDao = new FrozentimeDaoImpl();
    private UserDao userDao = new UserDaoImpl();



    @Override
    public ArrayList<FrozentimePerfect> queryFrozentime() {
        ArrayList<Frozentime> frozentimes = frozentimeDao.queryFrozentime();
        ArrayList<FrozentimePerfect> ftp = new ArrayList<>();
        //双表查询
        for (Frozentime frozentime : frozentimes) {
            Users users = userDao.findById(frozentime.getUserId());
            FrozentimePerfect fro=new FrozentimePerfect(frozentime,users);
            ftp.add(fro);
        }
        return ftp;

    }

    @Override
    public FrozentimePerfect queryByUser(int userId) {
        ArrayList<FrozentimePerfect> frozentimePerfects = queryFrozentime();
        for (FrozentimePerfect frozentimePerfect : frozentimePerfects) {
            if (frozentimePerfect.getUsers().getId()==userId){
                return  frozentimePerfect;
            }
        }
        return null;
    }

    @Override
    public boolean updateUserState(int id, Frozentime frozentime) {
        ArrayList<Frozentime> frozentimes = frozentimeDao.queryFrozentime();
        //查询ID是否存在
        boolean flag=false;
        for (Frozentime f : frozentimes) {
            if(f.getId()==id){
                flag=true;
            }
        }

        if (flag){
            frozentimeDao.updateUserState(id,frozentime);
        }
        return false;
    }
}
