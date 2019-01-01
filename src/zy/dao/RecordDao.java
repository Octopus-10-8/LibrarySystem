package zy.dao;

import zy.entity.Records;
import zy.entity.Users;

import java.util.ArrayList;

public interface RecordDao {

     void save(Records records);


     ArrayList<Records> queryAllRecords();


      Records queryById(int recordsID);



     void  updateRecord(Records records);





}
