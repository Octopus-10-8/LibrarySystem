package zy.biz;

import zy.entity.Comments;
import zy.entity.CommentsPerfect;

import java.util.ArrayList;

public interface CommentsBiz {


    public ArrayList<CommentsPerfect> queryAllComments();

    public ArrayList<CommentsPerfect> queryByBiid(int biid);

    public void addComments(Comments comments);

}
