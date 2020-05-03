package com.zcoox.mmalljob.order.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zcoox.boot.elasticjob.annotation.ElasticJobBean;
import com.zcoox.mmalljob.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
//@ElasticJobBean(
//        name = "orderBuilderJob",
//        cron = "0/10 * * * * ?",
//        overwrite = true
//)
public class OrderBuilderJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        for (int i = 0; i < 10; i++) {
            orderService.insert();
        }
    }

}
