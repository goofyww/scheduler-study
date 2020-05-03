package com.zcoox.mmalljob.order.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.zcoox.mmalljob.order.dao.extend.JdOrderExtendDao;
import com.zcoox.mmalljob.order.dao.extend.TmOrderExtendDao;
import com.zcoox.mmalljob.order.entity.CollectOrder;
import com.zcoox.mmalljob.order.entity.JdOrder;
import com.zcoox.mmalljob.order.entity.TmOrder;
import com.zcoox.mmalljob.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

//@ElasticJobBean(
//        name = "fetchThirdOrderJob",
//        cron = "0/15 * * * * ?",
//        shardingTotalCount = 2,
//        overwrite = true
//)
public class FetchThirdOrderJob implements DataflowJob<Object> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JdOrderExtendDao jdOrderExtendDao;

    @Autowired
    private TmOrderExtendDao tmOrderExtendDao;

    @Override
    public List<Object> fetchData(ShardingContext shardingContext) {

        if (shardingContext.getShardingItem() == 0) {
            //京东订单
            List<JdOrder> jdOrders = jdOrderExtendDao.getNotFetchedOrder(5);
            if (jdOrders != null && jdOrders.size() > 0) {
                List<Object> jdOrderList = jdOrders.stream().map(jdOrder -> (Object) jdOrder).collect(toList());
                return jdOrderList;
            }
        } else {
            //天猫订单
            List<TmOrder> tmallOrders = tmOrderExtendDao.getNotFetchedOrder(5);
            if (tmallOrders != null && tmallOrders.size() > 0) {
                List<Object> tmallOrderList = tmallOrders.stream().map(tmallOrder -> (Object) tmallOrder).collect(toList());
                return tmallOrderList;
            }
        }
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Object> data) {
        if (shardingContext.getShardingItem() == 0) {
            //京东订单
            if (data != null && data.size() > 0) {
                List<JdOrder> jdOrders = data.stream().map(d -> (JdOrder) d).collect(toList());
                for (JdOrder jdOrder : jdOrders) {
                    CollectOrder allOrder = new CollectOrder();
                    allOrder.setThirdOrderId(jdOrder.getId());
                    allOrder.setType(0);//京东订单
                    allOrder.setTotalAmount(jdOrder.getAmount());
                    allOrder.setCreateUser("system");
                    allOrder.setCreateTime(new Date());
                    allOrder.setUpdateUser("system");
                    allOrder.setUpdateTime(new Date());
                    orderService.processJdOrder(allOrder);
                }
            }
        } else {
            //天猫订单
            if (data != null && data.size() > 0) {
                List<TmOrder> tmallOrders = data.stream().map(d -> (TmOrder) d).collect(toList());
                for (TmOrder tmallOrder : tmallOrders) {
                    CollectOrder allOrder = new CollectOrder();
                    allOrder.setThirdOrderId(tmallOrder.getId());
                    allOrder.setType(1);//天猫订单
                    allOrder.setTotalAmount(tmallOrder.getMoney());
                    allOrder.setCreateUser("system");
                    allOrder.setCreateTime(new Date());
                    allOrder.setUpdateUser("system");
                    allOrder.setUpdateTime(new Date());
                    orderService.processTmallOrder(allOrder);
                }
            }
        }
    }
}
