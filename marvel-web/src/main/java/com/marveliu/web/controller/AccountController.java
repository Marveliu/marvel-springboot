package com.marveliu.web.controller;


import com.marveliu.web.component.page.Result;
import com.marveliu.web.constant.ResultCodeEnum;
import com.marveliu.web.constant.UserEnum;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.mapper.UserMapper;
import com.marveliu.web.domain.param.RegUserParam;
import com.marveliu.web.service.AccountService;
import com.marveliu.web.service.UserService;
import com.marveliu.web.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:14
 * @Description:
 **/

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController extends BasicAction {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    /**
     * 这里已经在 passwordFilter 进行了登录认证
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public Result accountLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = HttpUtil.getRequestBodyMap(request);
        String appId = params.get("username");
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRole(appId);

        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        String jwt = JwtUtil.issueJWT(UUID.randomUUID().toString(), appId.toString(),
                "token-server", refreshPeriodTime >> 1, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, jwt, refreshPeriodTime, TimeUnit.SECONDS);

        User user = userService.getDAO().findUserByUsername(appId);
        user.setPassword(null);
        user.setSalt(null);

        // LogExeManager.getInstance().executeLogTask(LogTaskFactory.loginLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "登录成功"));
        return Result.oK(ResultCodeEnum.JWT_ISSUE_SUCCESS).addData("jwt", jwt).addData("user", user);
    }

    /**
     * 用户账号的注册
     *
     * @param regUserParam
     * @param request
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public Result accountRegister(@Valid @RequestBody RegUserParam regUserParam, HttpServletRequest request) {

        if (!ObjectUtils.isEmpty(userService.getDAO().findUserByUsername(regUserParam.getUsername()))) {
            // 账户已存在
            return Result.error(ResultCodeEnum.USER_REG_FAIL).message("用户名已存在");
        }

        // 从Redis取出密码传输加密解密秘钥
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + regUserParam.getUserKey());
        String realPassword = AESUtil.aesDecode(regUserParam.getPassword(), tokenKey);

        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        regUserParam.setPassword(MD5Util.md5(realPassword + salt));

        User user = userMapper.from(regUserParam, salt);
        user.setStatus(UserEnum.NOMAL.getCode());

        if (accountService.registerAccount(user)) {
            // LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
            return Result.oK(ResultCodeEnum.USER_REG_SUCCESS);
        } else {
            // LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return Result.error(ResultCodeEnum.USER_REG_FAIL);
        }
    }

}
