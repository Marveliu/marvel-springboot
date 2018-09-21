package com.marveliu.web.exception;

import com.marveliu.common.exceptions.BaseExcetion;

/**
 * @Author: Marveliu
 * @Date: 2018/9/21 下午3:07
 * @Description:
 **/

public class UserNotExistException extends BaseExcetion {

    private String id;

    public UserNotExistException(String message) {
        super(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
