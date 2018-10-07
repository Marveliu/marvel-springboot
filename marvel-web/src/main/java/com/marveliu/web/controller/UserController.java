package com.marveliu.web.controller;

import com.marveliu.web.component.page.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午12:05
 * @Description:
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户查询
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:view")
    public Result user() {
        try {
            return Result.oK();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 用户添加
     *
     * @return
     */
    @PutMapping("/add")
    @RequiresPermissions("user:add")
    public Result userAdd() {
        try {
            return Result.oK();
        } catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 用户删除
     *
     * @return
     */
    @GetMapping("/del")
    @RequiresPermissions("user:del")
    public Result userDel() {
        try {
            return Result.oK();
        } catch (Exception e) {
            return Result.error();
        }
    }
}
