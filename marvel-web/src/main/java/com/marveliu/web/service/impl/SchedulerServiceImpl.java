package com.marveliu.web.service.impl;

import com.marveliu.web.quartz.BaseJob;
import com.marveliu.web.service.SchedulerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/17 下午11:18
 * @Description:
 **/

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;


    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

    @Override
    public void addJob(JobDetail jobDetail, Trigger trigger) throws Exception {
        // 启动调度器
        try {
            scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("创建任务调度失败", e);
        }
    }


    @Override
    public void modifyJob(Trigger oldTrigger, Trigger newTrigger) throws Exception {
        try {
            scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
        } catch (SchedulerException e) {
            logger.error("修改任务调度失败", e);
        }
    }

    @Override
    public List<Trigger> queryJob(JobDetail jobDetail) throws Exception {
        try {
            return (List<Trigger>) scheduler.getTriggersOfJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            logger.error("修改任务调度失败", e);
        }
        return null;
    }


    @Override
    public Boolean delJob(JobDetail jobDetail) {
        try {
            return scheduler.deleteJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            logger.error("删除任务调度失败", e);
        }
        return null;
    }

    @Override
    public Boolean deleteJob(JobDetail jobDetail, Trigger trigger) throws Exception {
        scheduler.pauseTrigger(trigger.getKey());
        scheduler.unscheduleJob(trigger.getKey());
        return scheduler.deleteJob(jobDetail.getKey());
    }

    @Override
    public Boolean deleteJob(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        return scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @Override
    public Boolean isExisted(JobKey jobKey) throws Exception {
        return scheduler.checkExists(jobKey);
    }

    @Override
    public void addCronJob(String jobClassName, String jobGroupName, JobDataMap jobDataMap, String cronExpression) throws Exception {
        //todo: cronJob
    }

    @Override
    public Boolean queryJob(String jobClassName, String jobGroupName) throws Exception {
        //todo: cronJob
        return null;
    }
}
