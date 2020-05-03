package com.zcoox.mmalljob.order.dao;

import com.zcoox.mmalljob.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}