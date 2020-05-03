package com.zcoox.mmalljob.order.service;

import com.zcoox.mmalljob.order.entity.CollectOrder;
import com.zcoox.mmalljob.order.entity.Order;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface OrderService {

    int insert();

    List<Order> getOrder(Calendar now, int shardingTotalCount, int shardingItem);

    void cancelOrder(Integer orderId, Date updateTime, int state, String updateUser, Date updateNow);

    void produceThirdOrder();

    void processJdOrder(CollectOrder allOrder);

    void processTmallOrder(CollectOrder allOrder);
}
