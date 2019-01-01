package zy.dao;

import zy.entity.BookInfoAndBooks;
import zy.entity.Bookinfo;
import zy.entity.Books;

import java.util.ArrayList;

public interface BookinfoDao {


     void save(Bookinfo bookinfo);

     ArrayList<Bookinfo> queryAllLib();


     void bookinfoDelete(int biid);

     Bookinfo queryOneBookInfo(int biid);


     void updateBookInfoInorout(int biid, String string);

     void updateBookInfoState(int biid, String string);

     void updateBookInfoLost(int biid, String string);


}
