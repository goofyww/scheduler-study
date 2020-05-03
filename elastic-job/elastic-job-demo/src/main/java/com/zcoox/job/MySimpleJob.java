package com.zcoox.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.time.LocalDateTime;

/**
 * 简单作业
 */
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(LocalDateTime.now() + ":当前分片值：" +
                shardingContext.getShardingItem() + "，总分片数：" +
                shardingContext.getShardingTotalCount());
    }
}
