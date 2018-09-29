package com.marveliu.common.constants;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:06 PM
 **/

public enum ResultCode {

    ERROR(-1, "error"),
    SUCCESS(0, "success");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
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