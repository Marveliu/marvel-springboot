package com.marveliu.web.util;

import com.alibaba.fastjson.JSON;
import com.marveliu.web.filter.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:29
 * @Description: Http工具类
 **/

@Slf4j
public class HttpUtil {

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 取request中的已经被防止XSS，SQL注入过滤过的key value数据封装到map 返回
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParameters(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = (String) enums.nextElement();
            String paraValue = HttpUtil.getRequest(request).getParameter(paraName);
            if (null != paraValue && !"".equals(paraValue)) {
                dataMap.put(paraName, paraValue);
            }
        }
        return dataMap;
    }

    /**
     * 获取request中的body json 数据转化为map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getRequestBodyMap(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        // 判断是否已经将 inputStream 流中的 body 数据读出放入 attribute
        if (request.getAttribute("body") != null) {
            // 已经读出则返回attribute中的body
            return (Map<String, String>) request.getAttribute("body");
        } else {
            try {
                Map<String, String> maps = JSON.parseObject(request.getInputStream(), Map.class);
                dataMap.putAll(maps);
                request.setAttribute("body", dataMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataMap;
        }
    }

    /**
     * 读取request 已经被防止XSS，SQL注入过滤过的 请求参数key 对应的value
     *
     * @param request
     * @param key
     * @return
     */
    public static String getParameter(ServletRequest request, String key) {
        return HttpUtil.getRequest(request).getParameter(key);
    }

    /**
     * 读取request 已经被防止XSS，SQL注入过滤过的 请求头key 对应的value
     *
     * @param request
     * @param key
     * @return
     */
    public static String getHeader(ServletRequest request, String key) {
        return HttpUtil.getRequest(request).getHeader(key);
    }

    /**
     * 取request头中的已经被防止XSS，SQL注入过滤过的 key value数据封装到map 返回
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestHeaders(ServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration enums = HttpUtil.getRequest(request).getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = (String) enums.nextElement();
            String value = HttpUtil.getRequest(request).getHeader(name);
            if (null != value && !"".equals(value)) {
                headerMap.put(name, value);
            }
        }
        return headerMap;
    }

    /**
     * Xss过滤
     *
     * @param request
     * @return
     */
    public static HttpServletRequest getRequest(ServletRequest request) {
        try {
            return new XssHttpServletRequestWrapper((HttpServletRequest) request, false);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 封装response  统一json返回
     *
     * @param outStr
     * @param response
     */
    public static void responseWrite(String outStr, ServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }
}
