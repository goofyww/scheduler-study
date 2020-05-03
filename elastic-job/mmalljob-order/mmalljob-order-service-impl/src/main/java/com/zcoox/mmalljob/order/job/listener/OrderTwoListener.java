package com.zcoox.mmalljob.order.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 每个作业节点都执行得监听器
 */
@Slf4j
public class OrderTwoListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("Method:OrderTwoListener,Job:{},方法之前运行", shardingContexts.getJobName());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("Method:OrderTwoListener,Job:{},方法之后运行", shardingContexts.getJobName());
    }
}
