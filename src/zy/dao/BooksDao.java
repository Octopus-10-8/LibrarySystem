package zy.dao;

import zy.entity.Books;
import java.util.ArrayList;

public interface BooksDao {

     void save(Books books);

     boolean booksUpdate(Books books);

     boolean booksDlete(int bid);

     ArrayList<Books> booksQuery();

     Books booksQueryById(int bid);

     boolean isHasByNameAndAuthor(Books books);


     boolean updateCount(int bid,int count);





}
