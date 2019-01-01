package zy.entity;

/**
 * Date: 2018/12/21 0021
 **/
public class BookInfoAndBooks {


    private Books books;     //图书编号
    private Bookinfo bookinfo;   //上架图书编号

    public BookInfoAndBooks(Books books, Bookinfo bookinfo) {
        this.books = books;
        this.bookinfo = bookinfo;
    }


    @Override
    public String toString() {
        return "图书信息={" + "上架图书编号=[" + bookinfo.getId() + "]\t书籍编号=[" + bookinfo.getBid() + "]\t是否在图书馆=[" + bookinfo.getInorout() + "]\t是否损坏=[" + bookinfo.getState() + "]\t是否丢失=[" +bookinfo.getLost()+ "]\r\n\t\t" + "\t书本名称=[" + books.getName()
                + "]\t图书类型=[" + books.getType() + "]\t图书作者=[" + books.getAuthor() + "]\t被借出次数=[" + books.getDiscount()+

        "]}\r\n";
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
