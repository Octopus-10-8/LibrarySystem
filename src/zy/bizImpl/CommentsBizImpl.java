package zy.bizImpl;

import zy.biz.CommentsBiz;
import zy.dao.BookinfoDao;
import zy.dao.BooksDao;
import zy.dao.CommentsDao;
import zy.dao.UserDao;
import zy.daoImpl.BookInfoImpl;
import zy.daoImpl.BooksDaoIml;
import zy.daoImpl.CommentsDaoImpl;
import zy.daoImpl.UserDaoImpl;
import zy.entity.*;

import java.util.ArrayList;

/**
 * Date: 2018/12/24 0024
 **/
public class CommentsBizImpl implements CommentsBiz {
    private CommentsDao commentsDao = new CommentsDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private BookinfoDao bookinfoDao = new BookInfoImpl();
    private BooksDao booksDao=new BooksDaoIml();

    @Override
    public ArrayList<CommentsPerfect> queryAllComments() {
        ArrayList<Comments> comments = commentsDao.queryAllComments();
        ArrayList<CommentsPerfect> cp = new ArrayList<>();
        for (Comments comment : comments) {
            Users byId = userDao.findById(comment.getUserID());
            Bookinfo bookinfo = bookinfoDao.queryOneBookInfo(comment.getBiid());
            Books books = booksDao.booksQueryById(bookinfo.getBid());
            CommentsPerfect perfect = new CommentsPerfect(comment, byId, bookinfo,books);
            cp.add(perfect);
        }
        return cp;
    }

    @Override
    public ArrayList<CommentsPerfect> queryByBiid(int biid) {
        ArrayList<CommentsPerfect> commentsPerfects = queryAllComments();
        ArrayList<CommentsPerfect> cp = new ArrayList<>();
        for (CommentsPerfect commentsPerfect : commentsPerfects) {
            if (commentsPerfect.getBookinfo().getId() == biid) {
                cp.add(commentsPerfect);
            }
        }
        return cp;
    }

    @Override
    public void addComments(Comments comments) {

        if (comments == null) {
            return;
        }
        commentsDao.addComments(comments);

    }
}
