package zy.biz;

import zy.entity.BookInfoAndBooks;
import zy.entity.Bookinfo;

import java.util.ArrayList;

public interface BookInfoBiz {


    boolean addLibBook(int bid);

    ArrayList<BookInfoAndBooks> queryBookInfo();

    Bookinfo queryOneBookInfo(int biid);

    boolean bookInfoDel(int biid);

    boolean updateBookInfoInorout(int biid, String string);

    boolean updateBookInfoState(int biid, String string);

    boolean updateBookInfoLost(int biid, String string);

    ArrayList<BookInfoAndBooks> borrowBookByKey(String key);

}
