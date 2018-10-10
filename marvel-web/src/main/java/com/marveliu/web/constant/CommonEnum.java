package com.marveliu.web.constant;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:41 PM
 **/

public enum CommonEnum {

    /**
     * 已删除
     */
    DEL_YES(1, "已删除"),

    /**
     * 未删除
     */
    DEL_NO(2, "已删除");


    private int code;
    private String msg;

    CommonEnum(int code, String msg) {
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
