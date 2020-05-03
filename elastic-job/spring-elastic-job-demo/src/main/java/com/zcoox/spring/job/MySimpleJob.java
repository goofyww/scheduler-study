package com.zcoox.spring.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("当前分片项：{}，总分片数：{}", shardingContext.getShardingItem(), shardingContext.getShardingTotalCount());
    }
}
