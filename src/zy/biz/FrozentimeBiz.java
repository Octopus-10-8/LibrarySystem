package zy.biz;

import zy.entity.Frozentime;
import zy.entity.FrozentimePerfect;

import java.util.ArrayList;

public interface FrozentimeBiz {


    ArrayList<FrozentimePerfect> queryFrozentime();

    FrozentimePerfect queryByUser(int userId);

    boolean updateUserState(int id, Frozentime frozentime);


}
