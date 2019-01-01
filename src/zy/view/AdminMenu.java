package zy.view;

import zy.biz.*;
import zy.bizImpl.*;
import zy.entity.*;
import zy.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Date: 2018/12/20 0020
 **/
public class AdminMenu {

    private Scanner scanner = new Scanner(System.in);
    private String chioce;

    private BooksBiz booksBiz = new BooksBizImpl();
    private BookInfoBiz bookInfoBiz = new BookInfoBizImpl();
    private FrozentimeBiz frozentimeBiz = new FrozentimeBizImpl();
    private UserBiz userBiz = new UserBizImpl();
    private OrderBookBiz orderBookBiz = new OrderBookBizImpl();

    /**
     * 管理员登陆中心
     */

    public void adminMenu() {
        System.out.println("=====欢迎来到管理员会员中心=====");
        System.out.println(" 1：管理员登陆");
        System.out.println(" 2：退出");
        chioce = scanner.next();
        switch (chioce) {
            case "1":
                adminLogin();
                break;
            case "2":
                return;
        }
    }

    /**
     * 管理员登陆
     */
    public void adminLogin() {
        System.out.println("=====管理员登陆中心=====");
        System.out.println("请输入管理员账号");
        String name = scanner.next();
        System.out.println("请输入管理员密码");
        String password = scanner.next();
        if (name.equals("123") && password.equals("123")) {
            System.out.println("登陆成功");
            adminManage();
        } else {
            System.out.println("登陆失败");
        }
    }

    /**
     * 管理中心
     */

    public void adminManage() {
        while (true) {
            System.out.println("=====图书馆后台管理大厅=====");
            System.out.println("1：图书馆书籍管理");
            System.out.println("2：图书馆图书信息上架管理");
            System.out.println("3：图书馆预约信息管理");
            System.out.println("4：用户管理中心");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    booksManage();
                    break;
                case "2":
                    bookInfoManage();
                    break;
                case "3":
                    orderBookMenu();
                    break;
                case "4":
                    userManageMenu();
                    break;
                default:
                    return;

            }

        }
    }

    /**
     * 图书馆书籍管理
     */

    public void booksManage() {

        while (true) {
            System.out.println("=====图书馆书籍管理======");
            System.out.println("1：添加书籍");
            System.out.println("2：删除书籍");
            System.out.println("3：修改书籍");
            System.out.println("4：查询书籍");
            System.out.println("5：返回上一层");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    booksAdd();
                    break;
                case "2":
                    booksUpdelete();
                    break;
                case "3":
                    booksUpdate();
                    break;
                case "4":
                    booksQuery();
                    break;
                case "5":
                    return;
            }

        }


    }

    /**
     * 添加书籍书表ID	书本名称	剩余数量	类型	作者	被借出次数	已借出数量
     * id	name	count	type	author	discount	hasLended
     */
    public void booksAdd() {
        while (true) {
            System.out.println("=====添加书籍界面=====");
            System.out.println("请输入书本名称");
            String name = scanner.next();
            String count = null;
            while (true) {
                System.out.println("请输入剩余数量(1~99)，注意不要输错");
                count = scanner.next();
                if (count.matches("(^[1-9][0-9]$)|(^100&)|(^[1-9]$)$")) {
                    break;
                }
            }
            System.out.println("请输入类型");
            String type = scanner.next();
            System.out.println("请输入作者");
            String author = scanner.next();
            Books books = new Books(name, Integer.parseInt(count), type, author);
            if (booksBiz.addBooks(books)) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }

        }


    }

    /**
     * 删除书籍
     */
    public void booksUpdelete() {
        while (true) {
            System.out.println("=====删除书籍界面=====");
            booksQuery();
            System.out.println("=====请输入书籍ID======");
            int id = scanner.nextInt();
            if (booksBiz.booksDlete(id)) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }
        }

    }

    /**
     * 修改书籍id	name	count	type	author	discount	hasLended
     */
    public void booksUpdate() {
        while (true) {
            System.out.println("=====修改书籍界面=====");
            booksQuery();
            int id = Utils.checkInput("=====请输入书籍ID======", 1, 1000);
            System.out.println("请输入书籍名称");
            String name = scanner.next();
            int count = Utils.checkInput("=====请输入图书剩余数量======", 1, 1000);
            System.out.println("请输入书籍类型");
            String type = scanner.next();
            System.out.println("请输入图书作者");
            String author = scanner.next();
            Books books = booksBiz.booksQueryById(id);
            if (books == null) {
                System.out.println("暂无该数据");
                break;
            }
            books.setName(name);
            books.setAuthor(author);
            books.setType(type);
            books.setCount(count);
            if (booksBiz.booksUpdate(books)) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }
        }

    }

    /**
     * 查询所有数据
     */
    public void booksQuery() {
        ArrayList<Books> arrayList = booksBiz.booksQuery();
        for (Books b : arrayList) {
            System.out.println(b);
        }
    }

    public void bookInfoManage() {
        while (true) {
            System.out.println("=====图书馆图书信息管理======");
            System.out.println("1：上架图书");
            System.out.println("2：下架图书信息");
            System.out.println("3：修改图书状态信息");
            System.out.println("4：查询图书馆图书");
            System.out.println("0：返回上一层");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    BookInfoAdd();
                    break;
                case "2":
                    bookInfoDel();
                    break;
                case "3":
                    bookInfoUpdateManage();
                    break;
                case "4":
                    queryAllBookinfo();
                    break;
                default:
                    return;
            }

        }


    }


    /**
     * 图书馆图书信息上架管理
     */
    public void BookInfoAdd() {

        while (true) {
            ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();
            Utils.showList(bookinfos, "上架所有信息");
            int bid = Utils.checkInput("请输入图书编号BID", 0, 1000);

            boolean b = bookInfoBiz.addLibBook(bid);
            if (b) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败，请检查图书编号是否填写正确");
            }
            if (!Utils.isGoOn()) {
                break;
            }
        }


    }


    public void queryAllBookinfo() {
        ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();

        if (bookinfos.size() == 0) {
            System.out.println("数据暂无");
        } else {
            for (BookInfoAndBooks bookinfo : bookinfos) {
                System.out.println(bookinfo);
            }
        }
    }

    /**
     * 图书下架信息
     */
    public void bookInfoDel() {
        while (true) {
            ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();
            Utils.showList(bookinfos, "请选择对应的ID进行下架");
            int delID = Utils.checkInput("请输入ID", 1, bookinfos.size());
            Bookinfo bookinfo = bookInfoBiz.queryOneBookInfo(delID);
            if (bookinfo == null) {
                System.out.println("无该数据");
                break;
            }
            if (bookInfoBiz.bookInfoDel(delID)) {
                System.out.println("下架成功");
            } else {
                System.out.println("下架失败");
            }


            if (!Utils.isGoOn()) {
                break;
            }

        }


    }

    /**
     * 图书信息修改管理（三个选项）
     */

    public void bookInfoUpdateManage() {
        while (true) {
            System.out.println("图书信息修改更新管理");
            System.out.println("1：是否在图书馆(修改)");
            System.out.println("2：是否损坏(修改)");
            System.out.println("3：是否丢失(修改)");
            System.out.println("4：返回上一层");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    bookInfoUpdateInorout();
                    break;
                case "2":
                    bookInfoUpdateState();
                    break;
                case "3":
                    bookInfoUpdateLost();
                    break;
                default:
                    return;
            }
        }


    }

    public void bookInfoUpdateInorout() {

        while (true) {
            ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();
            Utils.showList(bookinfos, "请选择对应的ID进行更新");
            int biid = Utils.checkInput("请输入ID", 1, 1000);
            System.out.println("请输入更新信息，只限于（在馆-离馆）");
            String inorout = scanner.next();
            boolean res = bookInfoBiz.updateBookInfoInorout(biid, inorout);
            if (res) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }

        }

    }


    public void bookInfoUpdateState() {
        while (true) {
            ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();
            Utils.showList(bookinfos, "请选择对应的ID进行更新");
            int biid = Utils.checkInput("请输入ID", 1, 1000);
            System.out.println("请输入更新信息，只限于（损坏-未损坏）");
            String state = scanner.next();
            boolean res = bookInfoBiz.updateBookInfoState(biid, state);
            if (res) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }

            if (!Utils.isGoOn()) {
                break;
            }

        }

    }

    public void bookInfoUpdateLost() {

        while (true) {
            ArrayList<BookInfoAndBooks> bookinfos = bookInfoBiz.queryBookInfo();
            Utils.showList(bookinfos, "请选择对应的ID进行更新");
            int biid = Utils.checkInput("请输入ID", 1, 1000);
            System.out.println("请输入更新信息，只限于（丢失-未丢失）");
            String lost = scanner.next();
            boolean res = bookInfoBiz.updateBookInfoLost(biid, lost);
            if (res) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            if (!Utils.isGoOn()) {
                break;
            }

        }
    }


    /**
     * 图书馆预约管理中心
     */
    public void orderBookMenu() {

        while (true) {
            System.out.println("===图书馆预约信息管理==");
            System.out.println("1:查询所有借阅信息");
            System.out.println("2:查看单个用户所有借阅信息");
            System.out.println("0:返回上一层");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    queryOrderAllBook();
                    break;
                case "2":
                    queryOneOrderBook();
                    break;
                case "0":
                    return;
            }
        }


    }

    /**
     * 查询所有信息
     */
    public void queryOrderAllBook() {
        ArrayList<OrderBookPerfect> orderBookPerfects = orderBookBiz.queryAllOrderBook();
        Utils.showList(orderBookPerfects, "图书馆预约信息总览");
    }

    /**
     * 查询某个人的的预约信息
     */
    public void queryOneOrderBook() {
        while (true) {
            ArrayList<Users> users = userBiz.queryAllUser();
            Utils.showList(users, "请选择要查询的用户");
            int userId = Utils.checkInput("请输入ID", 1, 1000);
            ArrayList<OrderBookPerfect> arrayList = orderBookBiz.queryOrderBookByUser(userId);
            //注意这里传入的是user对象的账号
            if (arrayList == null) {
                System.out.println("无数据");
            } else {

                Utils.showList(arrayList, "用户预约信息");
            }
            if (!Utils.isGoOn()) {
                break;

            }
        }
    }


    /**
     * 用户管理中心
     */

    public void userManageMenu() {
        while (true) {
            System.out.println("=====图书馆用户管理中心======");
            System.out.println("1：用户信息查询");
            System.out.println("2：用户信息修改");
            System.out.println("3：用户冻结信息");
            System.out.println("4：用户解冻和冻结");
            System.out.println("0：返回上一层");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    queryUserMessage();
                    break;
                case "2":
                    updateUserMessage();
                    break;
                case "3":
                    userFrozenTime();
                    break;
                case "4":
                    userunFrozen();
                    break;
                default:
                    return;
            }

        }

    }


    /**
     * 用户信息查询
     */
    public void queryUserMessage() {
        ArrayList<Users> users = userBiz.queryAllUser();
        Utils.showList(users, "图书馆用户信息展示");
    }

    /**
     * 用户信息修改
     */

    public void updateUserMessage() {

        while (true) {
            queryUserMessage();
            System.out.println("请输入要修改的账号");   //账号和id都是唯一的
            String name = scanner.next();
            Users byName = userBiz.findByName(name);
            if (byName == null) {
                System.out.println("输入有误，用户不存在");
                break;
            } else {
                System.out.println("请输入修改后的密码");
                String password = scanner.next();
                int point = Utils.checkInput("请输入修改后的积分", -100, 100);
                int vip = Utils.checkInput("请输入修改后的等级", 0, 1);
                byName.setPasssword(password);
                byName.setPoint(point);
                byName.setLevel(vip);
                userBiz.update(byName);
                System.out.println("数据修改成功");
            }

        }


    }


    //冻结信息
    public void userFrozenTime() {
        while (true) {
            ArrayList<FrozentimePerfect> frozentimePerfects = frozentimeBiz.queryFrozentime();
            Utils.showList(frozentimePerfects, "冻结账号信息");
            if (!Utils.isGoOn()) {
                break;
            }
        }
    }


    //用户解冻
    public void userunFrozen() {
        while (true) {
            ArrayList<FrozentimePerfect> frozentimePerfects = frozentimeBiz.queryFrozentime();
            Utils.showList(frozentimePerfects, "冻结账号信息");
            int id = Utils.checkInput("请输入ID选择对应的记录", 1, 1000);
            String state = null;
            while (true) {
                System.out.println("请设置用户的冻结状态（冻结/解冻）");
                state = scanner.next();
                if (state.equals("冻结") || state.equals("解冻")) {
                    break;
                }
                System.out.println("请输入正确！");
                break;
            }

            if (state.equals("冻结")) {
                boolean b = frozentimeBiz.updateUserState(id, new Frozentime(id, Utils.getTime(), "无"));
                if (b) {
                    System.out.println("冻结成功");
                }

            }
            if (state.equals("冻结")) {
                boolean b = frozentimeBiz.updateUserState(id, new Frozentime(id, "无", Utils.getTime()));
                if (b) {
                    System.out.println("解冻成功");
                }

            }
            if (!Utils.isGoOn()) {
                break;
            }
        }


    }

}
