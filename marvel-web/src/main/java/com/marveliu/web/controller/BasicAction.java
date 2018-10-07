package com.marveliu.web.controller;

import com.marveliu.web.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:15
 * @Description: 获得来的request中的 key value数据封装到map返回
 **/

public abstract class BasicAction {

    @SuppressWarnings("rawtypes")
    protected Map<String, String> getRequestParameter(HttpServletRequest request) {
        return HttpUtil.getRequestParameters(request);
    }

    protected Map<String, String> getRequestBody(HttpServletRequest request) {
        return HttpUtil.getRequestBodyMap(request);
    }

    protected Map<String, String> getRequestHeader(HttpServletRequest request) {
        return HttpUtil.getRequestHeaders(request);
    }
}
