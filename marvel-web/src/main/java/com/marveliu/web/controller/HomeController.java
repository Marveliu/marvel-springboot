package com.marveliu.web.controller;

import com.marveliu.web.annotation.SLog;
import com.marveliu.web.component.page.Result;
import com.marveliu.web.util.MD5Util;
import com.marveliu.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午12:03
 * @Description:
 **/

@Slf4j
@RestController
@RequestMapping("")
public class HomeController {

    @GetMapping({"/", "/index"})
    public Result index() {
        return ResultUtil.success();
    }

    @SLog(msg = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) throws Exception {
        password = MD5Util.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultUtil.success();
        } catch (UnknownAccountException e) {
            return ResultUtil.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResultUtil.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultUtil.error("认证失败！");
        }
    }

}
