package zy.daoImpl;

import zy.dao.BookinfoDao;
import zy.entity.BookInfoAndBooks;
import zy.entity.Bookinfo;
import zy.entity.Books;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class BookInfoImpl extends BaseDao implements BookinfoDao {


    public BookInfoImpl() {
        super(new File("bookInfo.txt"));
    }


    /**
     * 上架书籍
     *
     * @param
     */
    @Override
    public void save(Bookinfo bookinfo) {
        ArrayList<Bookinfo> read = read();
        if (read.size() == 0) {
            bookinfo.setId(1);
        } else {
            bookinfo.setId(read.get(read.size() - 1).getId() + 1);
        }
        read.add(bookinfo);
        write(read);
        closeAll();
    }

    @Override
    public ArrayList<Bookinfo> queryAllLib() {
        ArrayList<Bookinfo> read = read();
        return read;
    }


    @Override
    public void bookinfoDelete(int biid) {
        ArrayList<Bookinfo> read = read();
        for (Bookinfo bookinfo : read) {
            if (bookinfo.getId() == biid) {
                read.remove(bookinfo);
                write(read);
                closeAll();
            }
        }
    }

    @Override
    public Bookinfo queryOneBookInfo(int biid) {
        ArrayList<Bookinfo> read = read();
        for (Bookinfo b : read) {
            if (b.getId() == biid) {
                return b;
            }
        }
        return null;
    }

    /**
     * 修改在馆的三种状态，在馆可以现在设置，丢失和损坏可以在还书的时候设置
     */
    @Override
    public void updateBookInfoInorout(int biid, String string) {
        ArrayList<Bookinfo> read = read();
        for (Bookinfo b : read) {
            if (b.getId() == biid) {
               b.setInorout(string);
               write(read);
               closeAll();
            }
        }

    }

    @Override
    public void updateBookInfoState(int biid, String string) {
        ArrayList<Bookinfo> read = read();
        for (Bookinfo b : read) {
            if (b.getId() == biid) {
                b.setState(string);
                write(read);
                closeAll();
            }
        }
    }

    @Override
    public void updateBookInfoLost(int biid, String string) {
        ArrayList<Bookinfo> read = read();
        for (Bookinfo b : read) {
            if (b.getId() == biid) {
                b.setLost(string);
                write(read);
                closeAll();
            }
        }
    }





}
