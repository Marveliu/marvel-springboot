package com.marveliu.web.handler;

import com.marveliu.web.component.page.Result;
import com.marveliu.web.exception.UnauthorizedException;
import com.marveliu.web.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
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
     * 主要用来捕获意想不到的异常，明确的异常需要定制实现
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result globalErrorHandler(Exception e) {
        /// TODO:打印出异常类名
        // log.error(e.getStackTrace()[1].getClassName()+"_",+e.getMessage(), e);
        log.error(e.getMessage(), e);
        return Result.error().message(e.getMessage());
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

    /**
     * 捕捉shiro的异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return Result.error().message("no permission");
    }

    /**
     * 捕捉UnauthorizedException
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401() {
        return Result.error().message("no permission");
    }


}
