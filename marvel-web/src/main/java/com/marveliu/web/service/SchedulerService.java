package com.marveliu.web.service;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:13
 * @Description: 任务调度
 **/

public interface SchedulerService {

    void addJob(JobDetail jobDetail, Trigger trigger) throws Exception;

    void addCronJob(String jobClassName, String jobGroupName, JobDataMap jobDataMap, String cronExpression) throws Exception;

    void modifyJob(Trigger oldTrigger, Trigger newTrigger) throws Exception;

    Boolean delJob(JobDetail jobDetail);

    Boolean isExisted(JobKey jobKey) throws Exception;

    Boolean queryJob(String jobClassName, String jobGroupName) throws Exception;

    Boolean deleteJob(String jobClassName, String jobGroupName) throws Exception;

    Boolean deleteJob(JobDetail jobDetail, Trigger trigger) throws Exception;

    List<Trigger> queryJob(JobDetail jobDetail) throws Exception;
}
