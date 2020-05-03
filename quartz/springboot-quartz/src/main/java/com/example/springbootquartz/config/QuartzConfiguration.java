package com.example.springbootquartz.config;

import com.example.springbootquartz.job.MyJob3;
import com.example.springbootquartz.listener.MyTriggerListener;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz 配置 方式一
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 配置JobDetail
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean myJob3JobDetail(MyJob3 myJob3) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(true);
        jobDetail.setTargetObject(myJob3);
        jobDetail.setTargetMethod("execute");
        return jobDetail;
    }

    /**
     * 配置触发器
     */
    @Bean
    public CronTriggerFactoryBean myJob3JobTrigger(JobDetail myJob3JobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(myJob3JobDetail);
        trigger.setCronExpression("0/10 * * * * ?");
        return trigger;
    }

    /**
     * 启动相关的定时任务
     */
    @Bean
    public SchedulerFactoryBean scheduler(Trigger myJob3JobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setStartupDelay(5);                // 延时启动，应用启动5秒后
        bean.setTriggers(myJob3JobTrigger);     // 注册触发器
        bean.setGlobalTriggerListeners(new MyTriggerListener());
        return bean;
    }
}
