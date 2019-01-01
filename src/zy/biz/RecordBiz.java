package zy.biz;

import zy.entity.Records;
import zy.entity.RecordsPerfect;
import zy.entity.Users;

import java.util.ArrayList;

public interface RecordBiz {

     boolean addRecords(Records records);


     ArrayList<RecordsPerfect> queryAllRecords();

     ArrayList<RecordsPerfect> queryUserRecords(Users users);

      boolean returnBook(int recordId);

      Records queryById(int recordsID);
}
