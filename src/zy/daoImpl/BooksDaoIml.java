package zy.daoImpl;

import zy.dao.BooksDao;
import zy.entity.Books;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/21 0021
 **/
public class BooksDaoIml extends BaseDao implements BooksDao {
    ArrayList<Books> booksRead = null;

    public BooksDaoIml() {
        super(new File("books.txt"));
    }

    @Override
    public void save(Books books) {
        booksRead = read();
        if (booksRead.size() == 0) {
            books.setId(1);
        } else {
            books.setId(booksRead.get(booksRead.size() - 1).getId() + 1);
        }
        booksRead.add(books);
        write(booksRead);
        closeAll();

    }

    @Override
    public boolean booksUpdate(Books books) {
        booksRead = read();
        for (int i = 0; i < booksRead.size(); i++) {
            if (booksRead.get(i).getId() == books.getId()) {
                booksRead.set(i, books);
                write(booksRead);
                closeAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean booksDlete(int bid) {
        booksRead = read();
        for (Books b : booksRead) {
            if (b.getId() == bid) {
                booksRead.remove(b);
                write(booksRead);
                closeAll();
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Books> booksQuery() {
        booksRead = read();
        return booksRead;
    }

    @Override
    public Books booksQueryById(int bid) {
        booksRead = read();
        for (Books b : booksRead) {
            if (b.getId() == bid) {
                return b;

            }
        }
        return null;
    }

    @Override
    public boolean isHasByNameAndAuthor(Books books) {
        booksRead = read();
        for (Books b : booksRead) {
            if (b.getName().equals(books.getName()) && b.getAuthor().equals(books.getAuthor())) {

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCount(int bid, int count) {
        booksRead = read();
        for (Books books : booksRead) {
            if (books.getId() == bid) {
               books.setCount(count);
               write(booksRead);
               closeAll();
               return  true;
            }
        }
        return false;
    }
}
