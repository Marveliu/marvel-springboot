package com.marveliu.web.config;

import com.google.common.collect.Maps;
import com.marveliu.web.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:30
 * @Description:
 **/

@Configuration
public class FilterConfig {


    @Bean
    public Filter getXssFilter() {
        return new XssFilter();
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
