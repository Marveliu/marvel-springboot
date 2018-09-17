package com.marveliu.web.utils;

import com.marveliu.web.annotation.SLog;
import com.marveliu.web.dao.entity.Log;
import com.marveliu.web.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:44
 * @Description: 处理系统log，分发消息
 **/

@Component
public class SLogUtil implements Runnable {

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SLogUtil.class);

    ExecutorService es;

    LinkedBlockingQueue<Log> queue;


    @PostConstruct
    public void init() {
        queue = new LinkedBlockingQueue<Log>();
        int c = Runtime.getRuntime().availableProcessors();
        es = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            es.submit(this);
        }
    }

    @PreDestroy
    public void close() throws InterruptedException {
        // 触发关闭
        queue = null;
        if (es != null && !es.isShutdown()) {
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    /**
     * 异步插入日志
     *
     * @param Logs 日志对象
     */
    public void async(Log Logs) {
        LinkedBlockingQueue<Log> queue = this.queue;
        if (queue != null)
            try {
                boolean re = queue.offer(Logs, 50, TimeUnit.MILLISECONDS);
                if (!re) {
                    logger.info("syslog queue is full, drop it ...");
                }
            } catch (InterruptedException e) {
            }
    }

    /**
     * 同步插入日志
     *
     * @param log 日志对象
     */
    public void sync(Log log) {
        try {
            logService.save(log);
        } catch (Throwable e) {
            logger.info("insert syslog sync fail", e);
        }
    }

    @Override
    public void run() {
        while (true) {
            LinkedBlockingQueue<Log> queue = this.queue;
            if (queue == null)
                break;
            try {
                Log Logs = queue.poll(1, TimeUnit.SECONDS);
                if (Logs != null) {
                    sync(Logs);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * SLog Aop 调用
     *
     * @param log
     * @param async
     */
    public void log(Log log, Boolean async) {

        if (async) {
            async(log);
        } else {
            sync(log);
        }
    }


    /**
     * 记录操作记录
     *
     * @param sLog
     * @param elLog
     * @param src
     */
    @Async
    public void makeLogs(SLog sLog, String elLog, String src) {
        Log log = new Log();
        log.setLog(elLog);
        log.setSrc(src);
        log.setStatus(0);
        log.setType(sLog.type());
        log.setTag(sLog.tag());
        this.log(log, sLog.async());
    }
}
