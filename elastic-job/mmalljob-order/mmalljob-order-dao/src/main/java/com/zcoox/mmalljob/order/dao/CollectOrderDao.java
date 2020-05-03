package com.zcoox.mmalljob.order.dao;

import com.zcoox.mmalljob.order.entity.CollectOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectOrderDao {
    
    int deleteByPrimaryKey(Integer id);

    int insert(CollectOrder record);

    int insertSelective(CollectOrder record);

    CollectOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectOrder record);

    int updateByPrimaryKey(CollectOrder record);
}