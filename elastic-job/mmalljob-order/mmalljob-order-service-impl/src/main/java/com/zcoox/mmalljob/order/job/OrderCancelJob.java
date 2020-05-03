package com.zcoox.mmalljob.order.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zcoox.mmalljob.order.entity.Order;
import com.zcoox.mmalljob.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
//@ElasticJobBean(name = "orderCancelJob")
public class OrderCancelJob implements SimpleJob {

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -30);

        List<Order> orders = orderService.getOrder(now, shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
        if (!CollectionUtils.isEmpty(orders)) {
            ExecutorService pool = Executors.newFixedThreadPool(4);
            orders.forEach(o -> {
                pool.execute(() -> {
                    // 更新条件
                    Integer orderId = o.getId();
                    Date updateTime = o.getUpdateTime();
                    // 更新内容
                    int state = 3;
                    String updateUser = "tom";
                    Date updateNow = new Date();
                    orderService.cancelOrder(orderId, updateTime, state, updateUser, updateNow);
                });
            });
            pool.shutdown();
        }
    }
}
