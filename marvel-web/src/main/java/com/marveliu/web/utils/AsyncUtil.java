package com.marveliu.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Author Marveliu
 * @Date 2018/9/16 下午7:30
 **/

@Component
public class AsyncUtil {
    private static final Logger logger = LoggerFactory.getLogger(AsyncUtil.class);

    private static Integer count = 0;

    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Async
    public void test() {
        count++;
        for (int n = 0; n < 10; n++) {
            if (ObjectUtils.isEmpty(threadLocal.get())) {
                threadLocal.set(0);
            } else {
                threadLocal.set(threadLocal.get() + 1);
            }
            System.out.println(String.format("%d个线程: %d 次值为:%d", count, n + 1, threadLocal.get()));
        }
    }

    @Async
    public void test1() {
        count++;
        for (int n = 0; n < 10; n++) {
            if (ObjectUtils.isEmpty(threadLocal.get())) {
                threadLocal.set(0);
            } else {
                threadLocal.set(threadLocal.get() + 1);
            }
            System.out.println(String.format("%d个线程: %d 次值为:%d", count, n + 1, threadLocal.get()));
        }
    }


}
