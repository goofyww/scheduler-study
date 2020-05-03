package com.zcoox.mmalljob.order.job.sharding;

import com.dangdang.ddframe.job.lite.api.strategy.JobInstance;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingStrategy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;

/**
 * 自定义分片策略
 */
public class OrderShardingStrategy implements JobShardingStrategy {

    /**
     * 自定义作业分片.
     *
     * @param instances          所有参与分片的单元列表
     * @param jobName            作业名称
     * @param shardingTotalCount 分片总数
     * @return 分片结果
     */
    @Override
    public Map<JobInstance, List<Integer>> sharding(List<JobInstance> instances, String jobName, int shardingTotalCount) {
        Map<JobInstance, List<Integer>> resultMap = Maps.newHashMap();
        ArrayDeque<Integer> deque = new ArrayDeque<>(shardingTotalCount);
        for (int i = 0; i < shardingTotalCount; i++) {
            deque.add(i);
        }
        while (!deque.isEmpty()) {
            instances.forEach(i -> {
                if (!deque.isEmpty()) {
                    Integer shardingItem = deque.pop();
                    List<Integer> integers = resultMap.get(i);
                    if (!CollectionUtils.isEmpty(integers)) {
                        integers.add(shardingItem);
                    } else {
                        List<Integer> integerList = Lists.newArrayList();
                        integerList.add(shardingItem);
                        resultMap.put(i, integerList);
                    }
                }
            });
        }
        return resultMap;
    }
}
