package com.marveliu.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午12:19
 * @Description:
 **/

@Slf4j
public class MD5Util {

    private static final String SALT = "marvel";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String md5(String content) {
        // 用于加密的字符
        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            // 使用平台默认的字符集将md5String编码为byte序列,并将结果存储到一个新的byte数组中
            byte[] byteInput = content.getBytes();
            // 信息摘要是安全的单向哈希函数,它接收任意大小的数据,并输出固定长度的哈希值
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // MessageDigest对象通过使用update方法处理数据,使用指定的byte数组更新摘要
            mdInst.update(byteInput);
            // 摘要更新后通过调用digest() 执行哈希计算,获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成16进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                str[k++] = md5String[byte0 & 0xf];
            }
            // 返回加密后的字符串
            return new String(str);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encrypt("test", "123456"));
    }
}
