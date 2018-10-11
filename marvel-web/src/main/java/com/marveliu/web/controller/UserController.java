package com.marveliu.web.controller;

import com.marveliu.web.component.page.Result;
import com.marveliu.web.service.AccountService;
import com.marveliu.web.service.UserService;
import com.marveliu.web.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午12:05
 * @Description:
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取对应用户角色", notes = "GET根据用户的appId获取对应用户的角色")
    @GetMapping("/role/{appId}")
    public Result getUserRoleList(@PathVariable String appId) {
        String roles = accountService.loadAccountRole(appId);
        Set<String> roleSet = JwtUtil.split(roles);
        return Result.oK().addData("roles", roleSet);
    }

    /**
     * 用户查询
     *
     * @return
     */
    @RequestMapping("/list")
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
    public Result userDel() {
        try {
            return Result.oK();
        } catch (Exception e) {
            return Result.error();
        }
    }
}
