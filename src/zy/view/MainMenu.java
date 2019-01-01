package zy.view;

import java.util.Scanner;

/**
 * Date: 2018/12/20 0020
 **/
public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
   private AdminMenu adminMenu = new AdminMenu();
    private UserMenu userMenu = new UserMenu();
    private String chioce;

    /**
     * 首页进入菜单
     */
    public void menu() {

        while (true) {
            System.out.println("=====图书馆管理系统=====");
            System.out.println("1：用户");
            System.out.println("2：管理员");
            System.out.println("3：退出");
            chioce = scanner.next();
            switch (chioce) {
                case "1":
                    userMenu.userMenu();
                    break;
                case "2":
                    adminMenu.adminMenu();
                    break;
                case "3":
                    break;
            }
        }

    }


}


