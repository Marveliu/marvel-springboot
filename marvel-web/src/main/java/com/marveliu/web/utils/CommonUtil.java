package com.marveliu.web.utils;

import java.util.Random;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:10
 * @Description: 高频方法工具类
 **/

public class CommonUtil {

    /**
     * 获取指定位数的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
