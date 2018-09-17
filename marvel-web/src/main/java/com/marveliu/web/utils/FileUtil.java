package com.marveliu.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:39
 * @Description: 文件上传工具楼
 **/

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    /**
     * 上传文件
     *
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        logger.debug("文件上传地址：" + targetFile.getAbsolutePath());
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 根据路径读取文件
     *
     * @param path 文件路径
     * @return
     */
    public static String readFromFile(String path) {
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[128];
        int len;
        try {
            while ((len = fileReader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}