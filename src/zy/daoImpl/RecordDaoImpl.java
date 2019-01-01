package zy.daoImpl;

import zy.dao.RecordDao;
import zy.entity.Records;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/22 0022
 **/
public class RecordDaoImpl extends BaseDao implements RecordDao {

    @Override
    public Records queryById(int recordsID) {
        ArrayList<Records> records = queryAllRecords();
        for (Records record : records) {
            if (record.getId() == recordsID) {
                return record;
            }
        }
        return null;
    }

    public RecordDaoImpl() {
        super(new File("record.txt"));
    }

    @Override
    public void save(Records records) {
        ArrayList<Records> read = read();
        if (read.size() == 0) {
            records.setId(1);
        } else {
            records.setId(read.get(read.size() - 1).getId() + 1);
        }
        read.add(records);
        write(read);
        closeAll();

    }

    @Override
    public ArrayList<Records> queryAllRecords() {

        return read();
    }

    @Override
    public void updateRecord(Records records) {
        ArrayList<Records> read = read();
        for (int i = 0; i < read.size(); i++) {
            if (read.get(i).getId() == records.getId()) {
                read.set(i, records);
                write(read);
                closeAll();

            }
        }


    }
}
