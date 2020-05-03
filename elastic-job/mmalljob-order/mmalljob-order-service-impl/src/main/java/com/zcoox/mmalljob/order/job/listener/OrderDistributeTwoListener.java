package com.zcoox.mmalljob.order.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 分布式场景监听器,仅单一节点执行,整个任务只有一头一尾
 */
@Slf4j
public class OrderDistributeTwoListener extends AbstractDistributeOnceElasticJobListener {

    public OrderDistributeTwoListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        log.info("Method:OrderDistributeTwoListener,Job:{},方法之前运行", shardingContexts.getJobName());
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        log.info("Method:OrderDistributeTwoListener,Job:{},方法之后运行", shardingContexts.getJobName());
    }
}
