package zy.dao;

import zy.entity.Frozentime;


import java.util.ArrayList;

public interface FrozentimeDao {


     void saveFrozen(Frozentime frozentime);


     ArrayList<Frozentime> queryFrozentime();


     void updateUserState(int id,Frozentime frozentime);


}
