package com.marveliu.web.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:37
 * @Description:
 **/

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    /**
     *
     */
    public static String AppName = "marvel";

    /**
     * 上传文件夹
     */
    public static String AppUploadFolder = "/pbocUpload/";

    /**
     * 文件上传路径
     */
    public static String AppUploadPath = "./pbocUpload/";

    /**
     * 文件上传路径
     */
    public static String AppUploadBase = "/uploads/";


    static {
        File dir = new File(AppUploadPath);
        if (!dir.exists()) {
            dir.mkdir();
            logger.info("初始化上传文件地址：" + dir.getAbsolutePath());
        }
    }
}
