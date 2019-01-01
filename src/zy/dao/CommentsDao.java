package zy.dao;

import zy.entity.Comments;
import zy.entity.Users;

import java.util.ArrayList;

public interface CommentsDao {

     ArrayList<Comments> queryAllComments();


     void addComments(Comments comments);

}
