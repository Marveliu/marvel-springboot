package com.marveliu.web.constant;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 上午10:56
 * @Description:
 **/

public enum UserEnum {

    NOMAL(1, "用户启用"),
    FORBIDDEN(2, "用户禁用");

    private int code;
    private String msg;

    UserEnum(int code, String msg) {
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
