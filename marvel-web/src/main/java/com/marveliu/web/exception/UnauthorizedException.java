package com.marveliu.web.exception;

/**
 * @Author: Marveliu
 * @Date: 2018/9/28 下午11:01
 * @Description: 未授权异常
 **/

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}

