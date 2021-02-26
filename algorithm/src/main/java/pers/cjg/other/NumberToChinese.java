package pers.cjg.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjingang@guazi.com    2021-02-25 22:11
 */
public class NumberToChinese {
    private static Map<Integer, String> /**/numToCh = new HashMap<>();

    static {
        numToCh.put(0, "零");
        numToCh.put(1, "一");
        numToCh.put(2, "二");
        numToCh.put(3, "三");
        numToCh.put(4, "四");
        numToCh.put(5, "五");
        numToCh.put(6, "六");
        numToCh.put(7, "七");
        numToCh.put(8, "八");
        numToCh.put(9, "九");
//        ...
        numToCh.put(10, "十");
        numToCh.put(100, "百");
        numToCh.put(1000, "千");
        numToCh.put(10000, "万");
        numToCh.put(100000000, "亿");
    }

    public static String func(String num) {
        String[] split = num.split("\\.");
        String before = split[0];

        String res = func4more(before);
        if (split.length == 2) {
            String after = split[1];
            res += "点" + func4more(after);

        }
        return res;
    }

    public static String func4more(String num) {

        Integer index = 1;

        String res = "";
        for (int i = 0; i < (num.length() + 4 - 1) / 4; i++, index *= 10000) {
            String substring = num.substring(
                    num.length() > (i + 1) * 4 ? num.length() - (i + 1) * 4 : 0,
                    num.length() > (i) * 4 ? num.length() - (i) * 4 : num.length()
            );
            String temp = func4(substring);
            // 加入单位
            if (index != 1) {
                if (!temp.equals("")) {
                    res = numToCh.get(index) + res;
                }
            }
            //转换数字
//            if (!res.equals(numToCh.get(0))||)
            res = temp + res;

        }

        return res;
    }

    public static String func4(String num) {

        char[] chars = num.toCharArray();
        String res = "";
        Integer index = 1;
        Boolean lastIfZero = false;
        for (int i = chars.length - 1; i >= 0; i--, index *= 10) {
            char aChar = chars[i];
            Integer integer = aChar - '0';

            // 加入单位
            if (index != 1) {
                if (integer == 0) {
                    if (!lastIfZero) {
                        res = numToCh.get(0) + res;
                    }
                } else {
                    res = numToCh.get(index) + res;
                }
            }
            //转换数字
            // 1、当前数字不是0
            // 2、上一次不是0
            if (integer == 0) {
                lastIfZero = true;
            }
            if (integer != 0) {
                res = numToCh.get(integer) + res;
                lastIfZero = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(func("123000010301.12345678910"));
    }
}
