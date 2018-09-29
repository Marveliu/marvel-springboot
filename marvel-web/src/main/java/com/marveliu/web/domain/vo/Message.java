package com.marveliu.web.domain.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午1:33
 * @Description:
 **/

public class Message {

    /**
     * 消息头meta 存放状态信息 code message
     */
    private Map<String, Object> meta = new HashMap<String, Object>();

    /**
     * 消息内容  存储实体交互数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public Map<String, Object> getMeta() {
        return meta;
    }

    public Message setMeta(Map<String, Object> meta) {
        this.meta = meta;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Message setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public Message addMeta(String key, Object object) {
        this.meta.put(key, object);
        return this;
    }

    public Message addData(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    public Message ok(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }

    public Message error(int statusCode, String statusMsg) {
        this.addMeta("success", Boolean.FALSE);
        this.addMeta("code", statusCode);
        this.addMeta("msg", statusMsg);
        this.addMeta("timestamp", new Timestamp(System.currentTimeMillis()));
        return this;
    }
}
