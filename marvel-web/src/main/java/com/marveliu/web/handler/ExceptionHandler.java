package com.marveliu.web.handler;

import com.marveliu.common.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:02 PM
 **/

public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Result globalErrorHandler(Exception ex) {

        logger.error(ex.toString());
        return null;
    }
}
