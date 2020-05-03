package com.zcoox.mmalljob.order.dao.extend;

import com.zcoox.mmalljob.order.dao.OrderDao;
import com.zcoox.mmalljob.order.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderExtendDao extends OrderDao {

    List<Order> getOrder(Date time, int shardingTotalCount, int shardingItem);

    void cancelOrder(Integer orderId, Date updateTime, int state, String updateUser, Date updateNow);
}