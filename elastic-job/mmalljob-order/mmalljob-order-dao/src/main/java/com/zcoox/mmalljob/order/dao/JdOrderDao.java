package com.zcoox.mmalljob.order.dao;

import com.zcoox.mmalljob.order.entity.JdOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface JdOrderDao {

    int deleteByPrimaryKey(Integer id);

    int insert(JdOrder record);

    int insertSelective(JdOrder record);

    JdOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JdOrder record);

    int updateByPrimaryKey(JdOrder record);
}