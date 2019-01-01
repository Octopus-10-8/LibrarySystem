package zy.entity;

/**
 * Date: 2018/12/24 0024
 **/
public class CommentsPerfect {


    private Comments comments;
    private Users users;
    private Bookinfo bookinfo;
    private  Books books;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }

    @Override
    public String toString() {
        return "评论{" +
                "用户名=[" + users.getName() +
                "]\t上架图书编号=[" + bookinfo.getId() +"]\t图书名称=["+books.getName()+
                "]\t评论=[" + comments.getComments() +"]"+
                '}';
    }

    public CommentsPerfect(Comments comments, Users users, Bookinfo bookinfo, Books books) {
        this.comments = comments;
        this.users = users;
        this.bookinfo = bookinfo;
        this.books = books;
    }
}
