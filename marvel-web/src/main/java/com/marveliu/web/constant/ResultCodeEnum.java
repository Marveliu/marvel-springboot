package com.marveliu.web.constant;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:06 PM
 **/

public enum ResultCodeEnum {

    ERROR(-1, "error"),
    SUCCESS(0, "success"),

    // 权限分配
    JWT_ISSUE_SUCCESS(1003, "issue jwt success"),

    // 注册
    USER_REG_SUCCESS(2002, "用户注册成功"),
    USER_REG_FAIL(1111, "用户注册失败");

    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}