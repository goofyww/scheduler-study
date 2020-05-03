package com.zcoox.mmalljob.order.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zcoox.mmalljob.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
//@ElasticJobBean(
//        name = "thirdOrderProduceJob",
//        cron = "0/5 * * * * ?",
//        overwrite = true
//)
public class ThirdOrderProduceJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        orderService.produceThirdOrder();
    }
}
