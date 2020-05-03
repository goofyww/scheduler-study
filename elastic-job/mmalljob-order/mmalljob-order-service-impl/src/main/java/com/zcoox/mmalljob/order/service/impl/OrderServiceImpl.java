package com.zcoox.mmalljob.order.service.impl;

import com.zcoox.mmalljob.order.dao.CollectOrderDao;
import com.zcoox.mmalljob.order.dao.JdOrderDao;
import com.zcoox.mmalljob.order.dao.OrderDao;
import com.zcoox.mmalljob.order.dao.TmOrderDao;
import com.zcoox.mmalljob.order.dao.extend.OrderExtendDao;
import com.zcoox.mmalljob.order.entity.CollectOrder;
import com.zcoox.mmalljob.order.entity.JdOrder;
import com.zcoox.mmalljob.order.entity.Order;
import com.zcoox.mmalljob.order.entity.TmOrder;
import com.zcoox.mmalljob.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderExtendDao orderExtendDao;

    @Autowired
    private JdOrderDao jdOrderDao;

    @Autowired
    private TmOrderDao tmOrderDao;

    @Autowired
    private CollectOrderDao collectOrderDao;

    @Override
    public int insert() {
        Order order = new Order();
        order.setAmount(BigDecimal.TEN);
        order.setState(1); // 1:未支付 2:已支付 3:已取消
        order.setReceiveName("小面包");
        order.setReceiveAddress("北京市海淀区西北旺镇");
        order.setReceiveMobile("13001217708");
        order.setCreateUser("goofyww");
        order.setCreateTime(new Date());
        order.setUpdateUser("goofyww");
        order.setUpdateTime(new Date());
        return orderDao.insertSelective(order);
    }

    @Override
    public List<Order> getOrder(Calendar now, int shardingTotalCount, int shardingItem) {
        return orderExtendDao.getOrder(now.getTime(), shardingTotalCount, shardingItem);
    }

    @Override
    public void cancelOrder(Integer orderId, Date updateTime, int state, String updateUser, Date updateNow) {
        orderExtendDao.cancelOrder(orderId, updateTime, state, updateUser, updateNow);
    }

    @Override
    public void produceThirdOrder() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            //京东订单
            if (randomInt == 0) {
                log.info("插入京东订单");
                JdOrder jdOrder = new JdOrder();
                jdOrder.setState(0);//未抓取
                jdOrder.setAmount(BigDecimal.TEN);
                jdOrder.setCreateUser("jdUser");
                jdOrder.setCreateTime(new Date());
                jdOrder.setUpdateUser("jdUser");
                jdOrder.setUpdateTime(new Date());
                jdOrderDao.insertSelective(jdOrder);
            } else {//天猫订单
                log.info("插入天猫订单");
                TmOrder tmOrder = new TmOrder();
                tmOrder.setOrderState(0);//未抓取
                tmOrder.setMoney(new BigDecimal(100));
                tmOrder.setCreateUser("tmallUser");
                tmOrder.setCreateTime(new Date());
                tmOrder.setUpdateUser("tmallUser");
                tmOrder.setUpdateTime(new Date());
                tmOrderDao.insertSelective(tmOrder);
            }
        }
    }

    @Transactional
    @Override
    public void processJdOrder(CollectOrder allOrder) {
        collectOrderDao.insertSelective(allOrder);
        JdOrder jdOrder = new JdOrder();
        jdOrder.setId(allOrder.getThirdOrderId());
        jdOrder.setState(1);//已抓取
        jdOrder.setUpdateUser("system");
        jdOrder.setUpdateTime(new Date());
        jdOrderDao.updateByPrimaryKeySelective(jdOrder);
    }

    @Transactional
    @Override
    public void processTmallOrder(CollectOrder allOrder) {
        collectOrderDao.insertSelective(allOrder);
        TmOrder tmallOrder = new TmOrder();
        tmallOrder.setId(allOrder.getThirdOrderId());
        tmallOrder.setOrderState(1);//已抓取
        tmallOrder.setUpdateUser("system");
        tmallOrder.setUpdateTime(new Date());
        tmOrderDao.updateByPrimaryKeySelective(tmallOrder);
    }
}
