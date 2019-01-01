package zy.biz;

import zy.entity.Books;

import java.util.ArrayList;

public interface BooksBiz {

     boolean addBooks(Books books);

     boolean booksUpdate(Books books);

     boolean booksDlete(int bid);

     ArrayList<Books> booksQuery();

     Books booksQueryById(int bid);
}
