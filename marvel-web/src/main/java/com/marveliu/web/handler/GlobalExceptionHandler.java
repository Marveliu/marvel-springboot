package com.marveliu.web.handler;

import com.marveliu.common.common.page.Result;
import com.marveliu.web.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:02 PM
 **/

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理实现
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result globalErrorHandler(Exception ex) {
        log.error(ex.toString());
        return null;
    }

    /**
     * UserNotExistException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }
}
