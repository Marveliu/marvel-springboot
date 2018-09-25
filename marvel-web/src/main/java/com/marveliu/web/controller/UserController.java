package com.marveliu.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
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
     * 用户查询.
     *
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("user:view")
    public String user() {
        return "user";
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("user:add")
    public String userAdd() {
        return "userAdd";
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("user:del")
    public String userDel() {
        return "userDel";
    }
}
