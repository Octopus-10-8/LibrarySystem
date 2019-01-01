package zy.bizImpl;

import zy.biz.BookInfoBiz;
import zy.dao.BookinfoDao;
import zy.dao.BooksDao;
import zy.daoImpl.BookInfoImpl;
import zy.daoImpl.BooksDaoIml;
import zy.entity.BookInfoAndBooks;
import zy.entity.Bookinfo;
import zy.entity.Books;

import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class BookInfoBizImpl implements BookInfoBiz {

    private BookinfoDao bookinfoDao = new BookInfoImpl();
    private BooksDao booksDao = new BooksDaoIml();

    /**
     * 图书上架功能
     *
     * @param bid
     * @return
     */
    @Override
    public boolean addLibBook(int bid) {
        Books books = booksDao.booksQueryById(bid);
        if (books == null || books.getCount() <= 0) {
            return false;
        }
        boolean b = booksDao.updateCount(bid, books.getCount() - 1);
        if (!b) {
            return false;
        }
        bookinfoDao.save(new Bookinfo(bid));
        return true;
    }


    /**
     * 图书上架信息查询
     *
     * @return
     */
    @Override
    public ArrayList<BookInfoAndBooks> queryBookInfo() {
        ArrayList<Bookinfo> bookinfos = bookinfoDao.queryAllLib();
        ArrayList<BookInfoAndBooks> bookInfoAndBooks = new ArrayList<>();
        for (Bookinfo bookinfo : bookinfos) {
            Books books = booksDao.booksQueryById(bookinfo.getBid());
            BookInfoAndBooks bookInB = new BookInfoAndBooks(books, bookinfo);
            bookInfoAndBooks.add(bookInB);
        }
        return bookInfoAndBooks;
    }

    @Override
    public boolean bookInfoDel(int biid) {
        // 如果对应biid存在的话删除，并且将对应的bid数量加一
        Bookinfo bookinfo = bookinfoDao.queryOneBookInfo(biid);
        Books books = booksDao.booksQueryById(bookinfo.getBid());
        if (booksDao.updateCount(books.getId(), books.getCount() + 1)) {
            bookinfoDao.bookinfoDelete(biid);
            return true;
        }
        return false;
    }


    @Override
    public boolean updateBookInfoInorout(int biid, String string) {
        if (string.equals("在馆") || string.equals("离馆")) {
            bookinfoDao.updateBookInfoInorout(biid, string);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBookInfoState(int biid, String string) {
        if (string.equals("损坏") || string.equals("未损坏")) {
            bookinfoDao.updateBookInfoState(biid, string);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBookInfoLost(int biid, String string) {
        if (string.equals("丢失") || string.equals("未丢失")) {
            bookinfoDao.updateBookInfoLost(biid, string);
            return true;
        }
        return false;
    }

    /***
     * 根据关键字查询图书
     */

    public ArrayList<BookInfoAndBooks> borrowBookByKey(String key) {
        //先查询所有的信息，在进行挑选
        ArrayList<BookInfoAndBooks> bookInfoAndBooks = queryBookInfo();
        ArrayList<BookInfoAndBooks> bookInfoBykey = new ArrayList<>();
        //关键字可以根据名称，作者，类型进行匹配都可以
        for (BookInfoAndBooks bIABook : bookInfoAndBooks) {
            if (bIABook.getBooks().getName().contains(key) || bIABook.getBooks().getAuthor().contains(key)
                    || bIABook.getBooks().getType().contains(key)) {
                bookInfoBykey.add(bIABook);
            }
        }
        return  bookInfoBykey;
    }

    @Override
    public Bookinfo queryOneBookInfo(int biid) {
        return bookinfoDao.queryOneBookInfo(biid);
    }
}
