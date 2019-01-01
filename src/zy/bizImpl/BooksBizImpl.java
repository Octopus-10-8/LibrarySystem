package zy.bizImpl;

import zy.biz.BooksBiz;
import zy.dao.BooksDao;
import zy.daoImpl.BooksDaoIml;
import zy.entity.Books;

import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class BooksBizImpl implements BooksBiz {

    private BooksDao booksDao = new BooksDaoIml();

    @Override
    public boolean addBooks(Books books) {
        if (!booksDao.isHasByNameAndAuthor(books)) {
            booksDao.save(books);
            return true;
        }
        return false;
    }

    @Override
    public boolean booksUpdate(Books books) {

        return booksDao.booksUpdate(books);
    }

    @Override
    public boolean booksDlete(int bid) {
        return booksDao.booksDlete(bid);
    }

    @Override
    public ArrayList<Books> booksQuery() {
        return booksDao.booksQuery();
    }

    @Override
    public Books booksQueryById(int bid) {
        return

                booksDao.booksQueryById(bid);
    }
}
