package com.marveliu.web.handler;

import com.google.gson.Gson;
import com.marveliu.common.constants.ResultCode;
import com.marveliu.common.utils.Result;
import com.marveliu.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:02 PM
 **/

public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result globalErrorHandler(Exception ex) {
        logger.info("GlobalControllerExceptionHandler");
        logger.error(ex.toString());

        String errorResult;

        // if(ex instanceof ParamException || ex instanceof ContractException || ex instanceof PermissionException || ex instanceof DataBaseException) {
        //     errorResult = ex.getMessage();
        // } else {
        //     errorResult = new Gson().toJson(ResultUtil.error(ResultCode.SYSTEM_ERROR));
        // }

        // return new Gson().fromJson(errorResult, Result.class);
        return null;
    }
}
