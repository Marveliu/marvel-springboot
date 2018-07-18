package com.marveliu.common.utils;

import com.marveliu.common.constants.ResultCode;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:03 PM
 **/

public class ResultUtil {

    //当正确时返回的值
    public static Result success(Object object, ResultCode resultCode) {
        Result result = new Result(resultCode.getCode());
        result.setData(object);
        result.setMsg(resultCode.getMsg());
        return result;
    }
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }


    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }


    // 默认错误返回
    public static Result error() {
        Result result = new Result(ResultCode.ERROR.getCode());
        result.setMsg(ResultCode.ERROR.getMsg());
        return result;

    }

    //当错误时返回的值
    public static Result error(String msg) {
        Result result = new Result(ResultCode.ERROR.getCode());
        result.setMsg(msg);
        return result;

    }

    public static Result error(ResultCode resultCode){
        Result result = new Result(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }
}
