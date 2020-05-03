package com.example.springbootquartz.service;

import com.example.springbootquartz.dao.OrderMapper;
import com.example.springbootquartz.dao.OrderStatisticsMapper;
import com.example.springbootquartz.model.Order;
import com.example.springbootquartz.model.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    
    public void createOrder() {
        Random random = new Random();
        int count = random.nextInt(5);
        for (int i = 0; i < count; i++) {
            Order order = new Order();
            order.setAmount(BigDecimal.TEN);
            order.setStatus(1);
            order.setReceiveName("xxxx");
            order.setReceiveAddress("XXXXX");
            order.setReceiveMobile("13811112222");
            order.setCreateTime(new Date());
            order.setCreateUser("test");
            order.setUpdateTime(new Date());
            order.setUpdateUser("test");
            orderMapper.insertSelective(order);
        }
    }


    public void orderStatistics() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        startDate.add(Calendar.MINUTE, -1);

        List<Order> orders = orderMapper.selectByDate(startDate.getTime(), now.getTime());

        OrderStatistics os = new OrderStatistics();
        os.setTime(now.getTime());
        os.setOrderCount(orders.size());
        os.setCreateUser("system");
        os.setCreateTime(new Date());
        os.setUpdateUser("system");
        os.setUpdateTime(new Date());
        orderStatisticsMapper.insertSelective(os);
    }
}
