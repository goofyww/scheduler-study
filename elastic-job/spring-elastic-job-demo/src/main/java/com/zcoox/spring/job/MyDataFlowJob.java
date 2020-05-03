package com.zcoox.spring.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyDataFlowJob implements DataflowJob<Integer> {

    private List<Integer> list = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
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
        System.out.println(LocalDateTime.now() + ":当前分片项:" + shardingContext.getShardingItem() + ",总分片项:" + shardingContext.getShardingTotalCount());
        return resultList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {
        list.removeAll(data);
        System.out.println(LocalDateTime.now() + ":当前分片项:" + shardingContext.getShardingItem() + ",移除的数据为:" + data);
    }
}
