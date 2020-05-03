package com.example.springbootquartz.config;

import com.example.springbootquartz.job.CreateOrderJob;
import com.example.springbootquartz.job.OrderStatisticsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置 方式二
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        JobDetail detail = JobBuilder.newJob(CreateOrderJob.class)
                .withIdentity("orderJob1", "group1")
                .storeDurably()
                .build();
        return detail;
    }

    @Bean
    public JobDetail myJobDetail2() {
        JobDetail detail = JobBuilder.newJob(OrderStatisticsJob.class)
                .withIdentity("orderStatisticsJob", "group1")
                .storeDurably()
                .build();
        return detail;
    }

    @Bean
    public Trigger myTrigger1() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity("orderTrigger1", "group1")
                .forJob(myJobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?")
                ).build();
        return trigger;
    }

    @Bean
    public Trigger myTrigger2() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withIdentity("orderTrigger2", "group1")
                .forJob(myJobDetail2())
                .withSchedule(CronScheduleBuilder.cronSchedule("5 0/1 * * * ?")
                ).build();
        return trigger;
    }
}
