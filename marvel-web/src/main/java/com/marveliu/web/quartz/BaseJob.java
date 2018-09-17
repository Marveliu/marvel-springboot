package com.marveliu.web.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author Marveliu
 * @Date 2018/9/13 下午11:20
 * @Description 任务调度Job接口
 **/

public interface BaseJob extends Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException;

}
