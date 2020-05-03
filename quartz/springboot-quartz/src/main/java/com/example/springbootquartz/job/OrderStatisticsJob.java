package com.example.springbootquartz.job;

import com.example.springbootquartz.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class OrderStatisticsJob extends QuartzJobBean {

    @Autowired
    private OrderService orderService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("订单统计执行！");
        orderService.orderStatistics();
    }
}
