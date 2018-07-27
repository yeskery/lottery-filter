package com.yeskery.filter;

/**
 * 数字格式化，数字位数填充
 * @author yeskery
 * @date 2018-07-27 16:04
 */
public class NumberFormatter {

    /**
     * 数字格式化，数字位数填充
     * @param num 数字
     * @param digit 总位数
     * @return 格式化后的字符串
     */
    public static String format(int num, int digit) {
        int min = (int) Math.pow(10D, (double) (digit - 1));
        if (num >= min) {
            return String.valueOf(num);
        }
        String data = String.valueOf(num);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < digit - data.length();i++) {
            stringBuilder.append(0);
        }
        return stringBuilder.append(data).toString();
    }

}
