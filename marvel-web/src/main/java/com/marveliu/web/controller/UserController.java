package com.marveliu.web.controller;

import com.marveliu.common.common.page.Result;
import com.marveliu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午12:05
 * @Description:
 **/

@Slf4j
@Controller
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
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
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
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
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
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error();
        }
    }
}
