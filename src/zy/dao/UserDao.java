package zy.dao;

import zy.entity.Users;

import java.util.ArrayList;

public interface UserDao {

     void save(Users user);

     Users findByName(String name);

     Users findById(int id);

     ArrayList<Users> queryAllUser();

     void update(Users users);










}
