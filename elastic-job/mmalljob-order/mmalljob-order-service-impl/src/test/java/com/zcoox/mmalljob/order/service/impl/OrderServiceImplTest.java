package com.zcoox.mmalljob.order.service.impl;

import com.zcoox.mmalljob.order.OrderServiceApplicationTests;
import com.zcoox.mmalljob.order.entity.Order;
import com.zcoox.mmalljob.order.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderServiceImplTest extends OrderServiceApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void insertTest() {
        System.out.println(orderService.insert());
    }

    @Test
    public void getOrder() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, -30);
        List<Order> orders = orderService.getOrder(now, 2, 1);
        System.out.println(orders.stream().map(Order::getId).collect(toList()));
    }
}
