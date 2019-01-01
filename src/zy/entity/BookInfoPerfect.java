package zy.entity;

/**
 * Date: 2018/12/21 0021
 **/
public class BookInfoPerfect {

    private Books books;
    private Bookinfo bookinfo;


    public BookInfoPerfect(Books books, Bookinfo bookinfo) {
        this.books = books;
        this.bookinfo = bookinfo;
    }


    @Override
    public String toString() {
        return "BookInfoPerfect{" +
                "books=" + books +
                ", bookinfo=" + bookinfo +
                '}';
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }
}
