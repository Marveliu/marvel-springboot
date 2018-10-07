package com.marveliu.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:47
 * @Description:
 **/

@Slf4j
public class AESUtil {


    /**
     * 默认加密秘钥 AES加密秘钥为约定16位，小于16位会报错
     */
    private static final String ENCODE_RULES = "tomSun28HaHaHaHa";

    /**
     * 默认算法
     */
    private static final String ALGORITHM_STR = "AES/CBC/PKCS5Padding";

    public static String aesEncode(String content) {
        return aesEncode(content, ENCODE_RULES);
    }

    public static String aesDecode(String content) {
        return aesDecode(content, ENCODE_RULES);
    }


    private AESUtil() {

    }

    /**
     * 加密 aes cbc模式
     *
     * @param content
     * @param encryptKey
     * @return
     */
    public static String aesEncode(String content, String encryptKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(), "AES");

            //根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(encryptKey.getBytes()));
            //获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes("utf-8");
            //根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            //将加密后的byte[]数据转换为Base64字符串
            return new String(Base64.encodeBase64(byteAES));
            //将字符串返回
        } catch (Exception e) {
            log.error("密文加密失败" + e.getMessage(), e);
            throw new RuntimeException("密文加密失败");
        }
    }

    /**
     * 解密
     *
     * @param content
     * @param decryptKey
     * @return
     */
    public static String aesDecode(String content, String decryptKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes("utf-8"), "AES");

            // 根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(decryptKey.getBytes("utf-8")));
            // 将加密并编码base64后的字符串内容base64解码成字节数组
            byte[] bytesContent = Base64.decodeBase64(content);
            // 解密
            byte[] byteDecode = cipher.doFinal(bytesContent);
            return new String(byteDecode, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            log.error("没有指定的加密算法::" + e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error("非法的块大小" + "::" + e.getMessage(), e);
            throw new RuntimeException("密文解密失败");
        } catch (NullPointerException e) {
            log.error("秘钥解析空指针异常" + "::" + e.getMessage(), e);
            throw new RuntimeException("秘钥解析空指针异常");
        } catch (Exception e) {
            log.error("秘钥AES解析出现未知错误" + "::" + e.getMessage(), e);
            throw new RuntimeException("密文解密失败");
        }
        //如果有错就返null
        return null;

    }

    public static void main(String[] args) {
        String[] keys = {
                "", "123456", "word"
        };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print(key + " | ");
            String encryptString = aesEncode(key, ENCODE_RULES);
            System.out.print(encryptString + " | ");
            String decryptString = aesDecode(encryptString, ENCODE_RULES);
            System.out.println(decryptString);
        }
    }
}
