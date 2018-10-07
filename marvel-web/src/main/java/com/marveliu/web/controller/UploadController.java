package com.marveliu.web.controller;

import com.marveliu.web.component.page.Result;
import com.marveliu.web.domain.vo.SysConfig;
import com.marveliu.web.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:40
 * @Description:
 **/

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private SysConfig sysConfig;

    /**
     * 用户头像上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/img")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            // todo:文件格式内容判断
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.debug("上传的文件名为：" + fileName);
            // 获取文件的后缀，比如图片的jpeg,png
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.debug("上传文件的后缀名为：" + suffixName);
            // 上传文件名
            String newFileName = UUID.randomUUID() + suffixName;
            log.debug("转换后的文件名：" + newFileName);
            FileUtil.uploadFile(file.getBytes(), sysConfig.getUploadPath(), newFileName);
            return Result.oK();
        } catch (Exception e) {
            log.error("上传用户头像失败", e);
        }
        return Result.error().message("用户头像上传失败");
    }
}
