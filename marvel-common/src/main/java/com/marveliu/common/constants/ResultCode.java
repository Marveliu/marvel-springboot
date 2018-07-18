package com.marveliu.common.constants;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:06 PM
 **/

public enum ResultCode {

    NORMAL(0, "启用"),
    EXAMINE(1, "待审核"),
    FOBIDDEN(-2, "禁用"),
    REJECT(-3, "已拒绝"),
    PARENT_FOBIDDEN(-4, "父机构被禁用"),
    DEPT_NOT_EXIST(-2001, "机构不存在"),
    DEPT_CODE_EXIST(-2002, "机构ID重复"),
    DEPT_NAME_EXIST(-2003, "机构名重复"),
    DEPT_PARENT_NOT_EXIST(-2004, "父机构不存在"),
    USER_NOT_EXIST(-3001, "用户不存在"),
    SYSTEM_ERROR(-9999, "系统错误"),

    OVERSTEP(-7777, "越级操作"),

    ERROR(-99, "ERROR"),
    SUCCESS(0, "success"),
    INVOKE_CONTRACT_ERROR(-8888, "调用合约失败"),
    USER_HAS_NOT_LOGIN(-5, "用户未登录"),
    DEPT_IS_NOT_EXIST(-5, "机构不存在"),
    SUPER_DEPT_IS_NOT_EXIST(-5, "上级机构不存在"),
    DEPT_HAS_SAME_NAME(-5, "同一层级下存在相同名称的机构"),
    CODE_IS_EXIST(-5, "编码已存在"),
    USER_IS_NOT_EXOST(-5, "用户不存在"),

    IMG_ERROR(-10004, "图片上传失败"),
    ACCOUNT_NOT_EMPTY(-10003, "Account不允许为空"),
    INVALID_STATUS(-10002, "用户已被限制登录"),
    PASSWORD_ERROR(-10000, "密码错误"),
    INVALID_ADDRESS(-10001, "合约地址错误"),
    NO_PERMISSION(-200, "没有权限");

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