package zy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * Date: 2018/12/20 0020
 **/
public class Utils {
    private static Scanner scanner = new Scanner(System.in);
    private static String chioce;

    public static boolean isGoOn() {
        System.out.println("是否继续、输入n/N退出");
        chioce = scanner.next();
        if (chioce.equalsIgnoreCase("n")) {
            return false;
        }
        return true;
    }


    /**
     * 输入检查
     */
    public static int checkInput(String name, int min, int max) {
        int res = 0;
        while (true) {
            System.out.println(name);
            String input = scanner.next();
            try {
                res = Integer.parseInt(input);
                if (res >= min && res <= max) {
                    break;
                } else {
                    System.out.println("请输入" + min + " ~" + max + "之间的值");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入不合法");
            }

        }
        return res;
    }





    public static <T> void showList(ArrayList<T> al, String str) {
        System.out.println("================" + str + "================");
        if (al.size() == 0) {
            System.out.println("没有数据");
        } else {
            for (T t : al) {
                System.out.println(t);
            }
        }
    }

    /***
     * 时间
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }

    /**
     * 更新时间至某一天
     */

    public static String updateDate(String nowTime,int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式

        try {
            Date date = dateFormat.parse(nowTime); // 指定日期
            Date newDate = null; // 指定日期加上20天
            newDate = addDate(date, day);
            return dateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(dateFormat.format(date));// 输出格式化后的日期
//        System.out.println(dateFormat.format(newDate));
        return null;
    }

    private static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


}
