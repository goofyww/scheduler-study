package com.zcoox.mmalljob.order.dao.extend;

import com.zcoox.mmalljob.order.dao.JdOrderDao;
import com.zcoox.mmalljob.order.entity.JdOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JdOrderExtendDao extends JdOrderDao {

    List<JdOrder> getNotFetchedOrder(@Param("count") int count);
}