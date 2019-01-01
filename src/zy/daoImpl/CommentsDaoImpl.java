package zy.daoImpl;

import jdk.nashorn.internal.ir.ReturnNode;
import zy.dao.CommentsDao;
import zy.entity.Comments;

import java.io.File;
import java.util.ArrayList;

/**
 * Date: 2018/12/24 0024
 **/
public class CommentsDaoImpl extends BaseDao implements CommentsDao {

    public CommentsDaoImpl() {
        super(new File("comments.txt"));
    }

    //查询所有评论
    @Override
    public ArrayList<Comments> queryAllComments() {

        ArrayList<Comments> read = read();
        return read;
    }

    //添加评论
    @Override
    public void addComments(Comments comments) {
        ArrayList<Comments> read = read();
        if (read.size() == 0) {
            comments.setId(1);
        } else {
            comments.setId(read.get(read.size() - 1).getId() + 1);
        }
        read.add(comments);
        write(read);
    }
}
