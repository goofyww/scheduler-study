package com.zcoox.boot.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.zcoox.boot.elasticjob.annotation.ElasticJobBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ElasticJobBean(
        name = "myBootDataFlowJob",
        cron = "0/10 * * * * ?",
        shardingTotalCount = 2,
        overwrite = true
)
public class MyBootDataFlowJob implements DataflowJob<Integer> {

    private List<Integer> list = new ArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            list.add(i + 1);
        }
    }

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        List<Integer> resultList = new ArrayList<>();
        for (Integer index : list) {
            if (index % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem()) {
                resultList.add(index);
                break;
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}:当前分片项:{},总分片项:{}", LocalDateTime.now(), shardingContext.getShardingItem(), shardingContext.getShardingTotalCount());
        return resultList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {
        if (CollectionUtils.isEmpty(data) || CollectionUtils.isEmpty(list)) {
            log.info("{}:当前分片项:{} list为空", LocalDateTime.now(), shardingContext.getShardingItem());
            return;
        }
        list.removeAll(data);
        try {
            Thread.sleep(5000);
            log.info("{}:当前分片项:{},移除的数据为:{}", LocalDateTime.now(), shardingContext.getShardingItem(), data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}