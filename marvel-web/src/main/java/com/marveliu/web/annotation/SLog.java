package com.marveliu.web.annotation;

import java.lang.annotation.*;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:37
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SLog {

    /**
     * 消息类型
     *
     * @return
     */
    int type() default 1;

    /**
     * 标签
     *
     * @return
     */
    String tag() default "系统通知";


    /**
     * @return
     */
    String msg() default "";

    /**
     * 是否记录传递参数
     *
     * @return 消息模板
     */
    boolean param() default false;

    /**
     * 记录执行结果
     *
     * @return 消息模板
     */
    boolean result() default false;

    /**
     * 是否异步执行,默认为true
     *
     * @return true, 如果需要异步执行
     */
    boolean async() default true;
}
