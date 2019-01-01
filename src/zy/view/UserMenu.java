package zy.view;

import zy.biz.*;
import zy.bizImpl.*;
import zy.entity.*;
import zy.utils.Utils;

import java.util.*;

/**
 * Date: 2018/12/20 0020
 **/
class UserMenu {
    private Scanner scanner = new Scanner(System.in);
    private String chioce;
    private UserBiz userBiz = new UserBizImpl();
    private OrderBookBiz orderBookBiz = new OrderBookBizImpl();
    private BookInfoBiz bookinfo = new BookInfoBizImpl();
    private RecordBiz recordBiz = new RecordBizImpl();
    private CommentsBiz commentsBiz = new CommentsBizImpl();
    private BooksBiz booksBiz = new BooksBizImpl();
    private Users usersName;
    private FrozentimeBiz frozentimeBiz = new FrozentimeBizImpl();


    /**
     * 用户登陆注册菜单
     */
    public void userMenu() {
        System.out.println("=====欢迎来到用户登陆中心 =====");
        System.out.println(" 1：用户登陆");
        System.out.println(" 2：用户注册");
        System.out.println(" 3：退出");
        chioce = scanner.next();
        switch (chioce) {
            case "1":
                userLogin();
                break;
            case "2":
                userRegist();
                break;
            case "3":
                return;
        }
    }

    /**
     * 用户登陆界面
     */
    private void userLogin() {
        while (true) {
            System.out.println("=====用户登录=====");
            System.out.println("请输入账号");
            String name = scanner.next();
            System.out.println("请输入密码");
            String password = scanner.next();
            if (userBiz.login(name, password)) {
                usersName = userBiz.findByName(name);
                boolean returnBook = userBiz.isReturnBook(usersName.getId());
                //先判断冻结在判断到期
                FrozentimePerfect frozentimePerfect = frozentimeBiz.queryByUser(usersName.getId());
                if (frozentimePerfect != null && frozentimePerfect.getFrozentime().getFrozentime().equals("冻结")) {
                    //此时说明处在冻结状态
                    System.out.println("您的账号被冻结,禁止登陆");
                    break;
                }
                if (returnBook) {

                    System.out.println("尊敬的<" + usersName.getName() + ">你借阅的书籍明天就要到期了");
                    System.out.println("1：立刻还书");
                    System.out.println("2：续借");
                    chioce = scanner.next();
                    switch (chioce) {
                        case "1":
                            returnBookMenu();
                            break;
                        case "2":
                            renewMenu();
                            break;
                        default:
                            break;

                    }

                }
                libForUserMenu();

            } else {
                System.out.println("登陆失败");
                return;
            }
        }
    }


    private void userRegist() {
        System.out.println("=====用户注册=====");
        System.out.println("请输入账号");
        String name = scanner.next();
        System.out.println("密码");
        String password = scanner.next();
        boolean regist = userBiz.regist(new Users(name, password));
        if (regist) {
            System.out.println("注册成功");

        } else {
            System.out.println("账号已存在，登陆失败");
        }


    }


    /**
     * 已登录用户功能大厅
     */

    private void libForUserMenu() {

        while (true) {
            System.out.println("=====在线图书馆=====");
            System.out.println("1: 图书预约");
            System.out.println("2：查询预约信息");
            System.out.println("3：借书");
            System.out.println("4：查看我的借阅信息");
            System.out.println("5：还书");
            System.out.println("6：续借");
            System.out.println("7：查看图书评论");
            System.out.println("8：查看我的评论");
            System.out.println("9：猜你喜欢");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    userLibOrder();
                    break;
                case "2":
                    userLibOrderShow();
                    break;
                case "3":
                    borrowBookMenu();
                    break;
                case "4":
                    borrowRecordMenu();
                    break;
                case "5":
                    returnBookMenu();
                    break;
                case "6":
                    renewMenu();
                    break;
                case "7":
                    commentsMenu();
                    break;
                case "8":
                    myComments();
                    break;
                case "9":
                    booksRecommended();
                    break;
                default:
                    return;
            }

        }
    }

    /**
     * 图书预约
     */

    private void userLibOrder() {

        while (true) {
            ArrayList<BookInfoAndBooks> bookInfoAndBooks = bookinfo.queryBookInfo();
            Utils.showList(bookInfoAndBooks, "请选择对应的图书编号");
            int biid = Utils.checkInput("请选择编号", 1, 1000);
            boolean res = orderBookBiz.addOrderBook(usersName.getId(), biid);
            if (res) {
                System.out.println("预约成功");
            } else {
                System.out.println("预约失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }

        }


    }

    /**
     * 用户预约信息
     */
    private void userLibOrderShow() {

        while (true) {
            ArrayList<OrderBookPerfect> orderBooks = orderBookBiz.queryAllOrderBook();
            Utils.showList(orderBooks, "所有预约信息");
            if (!Utils.isGoOn()) {
                break;
            }

        }
    }

    /**
     * 借书功能
     */
    private void borrowBookMenu() {

        while (true) {
            System.out.println("请输入关键词进行搜索（作者、书名、类型）");
            String key = scanner.next();
            ArrayList<BookInfoAndBooks> bookInfoAndBooks = bookinfo.borrowBookByKey(key);
            Utils.showList(bookInfoAndBooks, "根据<" + key + ">查询的结果，请选择对应的ID进行完成借阅");
            int id = Utils.checkInput("请选择对应的ID", 1, 10000);
            boolean b = recordBiz.addRecords(new Records(usersName.getId(), id, Utils.getTime(), Utils.updateDate(Utils.getTime(), 7)));
            if (b) {
                System.out.println("借阅成功");
            } else {
                System.out.println("借阅失败");
            }

            if (!Utils.isGoOn()) {
                break;
            }
        }
    }

    /**
     * 查询自己的借阅信息
     */
    private void borrowRecordMenu() {
        while (true) {
            ArrayList<RecordsPerfect> recordsPerfects = recordBiz.queryUserRecords(usersName);
            Utils.showList(recordsPerfects, "用户<" + usersName.getName() + ">的借阅信息");
            if (!Utils.isGoOn()) {
                break;
            }
        }


    }

    /**
     * 还书
     */
    private void returnBookMenu() {
        while (true) {
            ArrayList<RecordsPerfect> recordsPerfects = recordBiz.queryUserRecords(usersName);
            Utils.showList(recordsPerfects, "用户<" + usersName.getName() + ">的借阅信息");
            int id = Utils.checkInput("请选择对应的（记录）ID进行归还", 1, 1000);
            //在这里添加判断ID是否存在的情况，如果不存在，则直接跳出
            Bookinfo bookinfo = this.bookinfo.queryOneBookInfo(id);
            if (bookinfo==null){
                System.out.println("该图书你没有借，请输入正确");
                break;
            }
            System.out.println("请输入图书目前的损坏状态");
            String state = scanner.next();
            System.out.println("请输入图书目前的丢失状态");
            String lost = scanner.next();
            //根据记录ID获得biid
            Records records1 = recordBiz.queryById(id);
            this.bookinfo.updateBookInfoState(records1.getBookinfoid(), state);
            this.bookinfo.updateBookInfoLost(records1.getBookinfoid(), lost);
            System.out.println(this.bookinfo.queryOneBookInfo(records1.getBookinfoid()));
            if (recordBiz.returnBook(id)) {   //这里的ID是记录ID
                System.out.println("归还成功");
                System.out.println("是否评论？");
                String comments = scanner.next();
                if (comments.equals("是")) {
                    System.out.println("请输入评论");
                    String com = scanner.next();
                    //这里需要添加额根据记录查询对应的bookinfo对象
                    Records records = records1;
                    //Bookinfo bookinfo = this.bookinfo.queryOneBookInfo(records.getBookinfoid());
                    Comments commentsRes = new Comments(usersName.getId(), records.getBookinfoid(), com);
                    commentsBiz.addComments(commentsRes);
                    System.out.println("评论成功");
                }


            } else {
                System.out.println("归还失败，请检查书本是否完整，请查看相关还书规则");
            }
            if (!Utils.isGoOn()) {
                break;
            }
        }
    }


    /**
     * 续借
     */
    private void renewMenu() {
        while (true) {
            ArrayList<RecordsPerfect> recordsPerfects = recordBiz.queryUserRecords(usersName);
            Utils.showList(recordsPerfects, "用户<" + usersName.getName() + ">的借阅信息");
            int recordId = Utils.checkInput("请选择需要续借的图书记录的ID（记录ID）", 1, 1000);
            boolean b = userBiz.renewMenu(usersName.getId(), recordId);
            if (b) {
                System.out.println("续借成功");
            } else {
                System.out.println("续借失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }
        }
    }


    /**
     * 评论区
     */

    private void commentsMenu() {
        ArrayList<CommentsPerfect> commentsPerfects = commentsBiz.queryAllComments();
        Utils.showList(commentsPerfects, "所有图书评论");
    }

    /**
     *
     */
    private void myComments() {
        ArrayList<CommentsPerfect> commentsPerfects = commentsBiz.queryAllComments();
        boolean flag = false;
        for (CommentsPerfect commentsPerfect : commentsPerfects) {
            if (commentsPerfect.getUsers().getName().equals(usersName.getName())) {
                flag = true;
                System.out.println(commentsPerfect);
            }
        }
        if (flag == false) {
            System.out.println("您还没有评论过");
        }

    }


    /**
     * 猜你喜欢：用户通过选择猜你喜欢菜单，可以看到2种方案：1.如果没有接过书或者借书很少，可以推荐
     * * 书本表中经过排序后被借出次数最多的书籍2.如果该用户借过书，可以通过借过的书的类型和作者，
     * * 去book表中查询符合要求的书本信息3.特殊情况，如果该图书馆刚开张，那么就可以通过随机数
     * * 推荐几本书
     */

    private void booksRecommended() {


        while (true) {
            System.out.println("=====智能推荐=====");
            System.out.println("1: 随便看看");
            System.out.println("2：热门图书");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    havingLook();
                    break;
                case "2":
                    popularBooks();
                    break;
                default:
                    return;
            }

        }
    }

    /**
     * 随便看看()
     */
    private void havingLook() {
        while (true) {
            ArrayList<BookInfoAndBooks> bookInfoAndBooks = bookinfo.queryBookInfo();
            Random random = new Random();
            int next1 = random.nextInt(bookInfoAndBooks.size());
            int next2 = random.nextInt(bookInfoAndBooks.size());
            System.out.println("图书编号\t\t名称\t\t作者\t\t图书类型");
            for (BookInfoAndBooks bookInfoAndBook : bookInfoAndBooks) {
                if (bookInfoAndBook.getBookinfo().getId() == next1 || bookInfoAndBook.getBookinfo().getId() == next2) {
                    System.out.println("["+bookInfoAndBook.getBookinfo().getId() + "]\t\t\t[" + bookInfoAndBook.getBooks().getName()
                            + "]\t\t[" + bookInfoAndBook.getBooks().getAuthor() + "]\t\t\t[" + bookInfoAndBook.getBooks().getType()+"]");
                } else {
                    continue;
                }
            }
            System.out.println("下一个...");
            if (!Utils.isGoOn()) {
                break;
            }


        }
    }

    /**
     * 热门图书
     */
    private void popularBooks() {
        ArrayList<Books> books = booksBiz.booksQuery();

        Collections.sort(books, new Comparator<Books>() {
            @Override
            public int compare(Books o1, Books o2) {
                return o2.getDiscount() - o1.getDiscount();
            }
        });
        System.out.println("================本周最受欢迎图书排行===========");
        System.out.println("排名\t\t\t名称\t\t\t被借次数\t\t\t作者\t\t\t类型");
        int i = 1;
        for (Books book : books) {
            if (i < 11) {
                System.out.println("第" + (i++) + "名\t\t" + book.getName() + "\t\t\t" + book.getDiscount() + "\t\t\t" + book.getAuthor() + "\t\t\t" + book.getType());
            }
        }


    }


}
