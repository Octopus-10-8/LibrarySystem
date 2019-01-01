package zy.utils;

/**
 * Date: 2018/12/20 0020
 **/
public class Test {

    public static void main(String[] args) {
        String s = "sadasdasd";
     //   System.out.println(s.matches("^\\d{1,2}$"));   //只能是数字，并且只能是
        System.out.println(s.matches("(^[1-9][0-9]$)|(^100&)|(^[1-9]$)$"));

     //   System.out.println(Utils.updateDate(7));


    }
}
