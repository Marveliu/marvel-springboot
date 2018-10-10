package com.marveliu.web.component.page;

import com.marveliu.web.constant.ResultCodeEnum;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Marveliu
 * @Date 2018/7/18 9:05 PM
 **/

@Data
public class Result {

    /**
     * 消息头meta 存放状态信息 code message
     */
    private Map<String, Object> meta = new HashMap<String, Object>();

    /**
     * 消息内容  存储实体交互数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result addMeta(String key, Object object) {
        this.meta.put(key, object);
        return this;
    }

    public Result addData(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    /**
     * 默认成功返回值
     *
     * @return
     */
    public static Result oK() {
        return new Result().ok(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 返回状态码
     * o
     *
     * @param code
     * @return
     */
    public static Result oK(ResultCodeEnum code) {
        return new Result().ok(code.getCode(), code.getMsg());
    }

    /**
     * 默认失败返回值
     *
     * @return
     */
    public static Result error() {
        return new Result().error(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 返回状态码
     *
     * @param code
     * @return
     */
    public static Result error(ResultCodeEnum code) {
        return new Result().error(code.getCode(), code.getMsg());
    }

    /**
     * 统一使用resultCode
     *
     * @param statusCode
     * @param statusMsg
     * @return
     */
    private Result ok(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

    /**
     * 统一使用resultCode
     *
     * @param statusCode
     * @param statusMsg
     * @return
     */
    private Result error(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.FALSE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

    /**
     * 自定义message
     *
     * @param statusMsg
     * @return
     */
    public Result message(String statusMsg) {
        this.addMeta("msg", statusMsg);
        return this;
    }


}
