package com.zcoox.mmalljob.order.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zcoox.boot.elasticjob.annotation.ElasticJobBean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ElasticJobBean(name = "myShardingJob")
public class MyShardingJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("我是分片项:{}", shardingContext.getShardingItem());
    }
}
