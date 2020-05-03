package com.zcoox.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.zcoox.entity.Order;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据流式作业
 * 适用于不间歇的数据处理
 */
public class MyDataFlowJob implements DataflowJob<Order> {

    private List<Order> orders = new ArrayList<>();

    {
        for (int i = 0; i < 100; i++) {
            Order order = new Order(i + 1, 0);
            orders.add(order);
        }
    }

    /**
     * 抓取数据
     *
     * @param shardingContext
     * @return
     */
    @Override
    public List<Order> fetchData(ShardingContext shardingContext) {
        // 分片键 % 分片总数 == 当前分片项
        List<Order> orderList = orders.stream()
                .filter(o -> o.getState() == 0)
                .filter(o -> o.getId() % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem())
                .collect(Collectors.toList());

        List<Order> subList = null;
        if (orderList != null && orderList.size() > 0) {
            subList = orderList.subList(0, 10);
        }
        try {
            Thread.sleep(3000);
            LocalTime time = LocalTime.now();
            System.out.println("time:" + time + ",我是分片项：" + shardingContext.getShardingItem() + ",我抓取得数据是：" + subList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return subList;
    }

    /**
     * 处理数据
     *
     * @param shardingContext
     * @param list
     */
    @Override
    public void processData(ShardingContext shardingContext, List<Order> list) {

        list.forEach(o -> o.setState(1));
        LocalTime time = LocalTime.now();
        System.out.println("time:" + time + ",我是分片项：" + shardingContext.getShardingItem() + ",我正在处理数据！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
