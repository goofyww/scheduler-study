package com.zcoox.mmalljob.order.dao.extend;

import com.zcoox.mmalljob.order.dao.TmOrderDao;
import com.zcoox.mmalljob.order.entity.TmOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmOrderExtendDao extends TmOrderDao {

    List<TmOrder> getNotFetchedOrder(@Param("count") int count);
}