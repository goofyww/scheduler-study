package com.zcoox.boot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zcoox.boot.elasticjob.annotation.ElasticJobBean;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ElasticJobBean(
        name = "myBootSimpleJob"
)
public class MyBootSimpleJob implements SimpleJob {

    /**
     * 执行方法
     *
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info(LocalDateTime.now() + ":当前分片项:{}，总分片数:{}", shardingContext.getShardingItem(), shardingContext.getShardingTotalCount());
    }
}
