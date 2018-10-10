package com.marveliu.web.handler;

import com.marveliu.web.annotation.SLog;
import com.marveliu.web.dao.entity.SysLog;
import com.marveliu.web.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:44
 * @Description: 处理系统syslog，分发消息
 **/

@Slf4j
@Component
public class SysLogHandler implements Runnable {

    @Autowired
    private SysLogService sysLogService;

    ExecutorService es;

    LinkedBlockingQueue<SysLog> queue;


    @PostConstruct
    public void init() {
        queue = new LinkedBlockingQueue<SysLog>();
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
    public void async(SysLog sysLog) {
        LinkedBlockingQueue<SysLog> queue = this.queue;
        if (queue != null)
            try {
                boolean re = queue.offer(sysLog, 50, TimeUnit.MILLISECONDS);
                if (!re) {
                    log.info("syslog queue is full, drop it ...");
                }
            } catch (InterruptedException e) {
            }
    }

    /**
     * 同步插入日志
     *
     * @param slog 日志对象
     */
    public void sync(SysLog sysLog) {
        try {
            sysLogService.save(sysLog);
        } catch (Throwable e) {
            log.error("insert syslog sync fail", e);
        }
    }

    @Override
    public void run() {
        while (true) {
            LinkedBlockingQueue<SysLog> queue = this.queue;
            if (queue == null)
                break;
            try {
                SysLog Logs = queue.poll(1, TimeUnit.SECONDS);
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
    public void log(SysLog log, Boolean async) {

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
        SysLog sysLog = new SysLog();
        sysLog.setMsg(elLog);
        sysLog.setSrc(src);
        sysLog.setType(sLog.type());
        sysLog.setTag(sLog.tag());
        this.log(sysLog, sLog.async());
    }
}
