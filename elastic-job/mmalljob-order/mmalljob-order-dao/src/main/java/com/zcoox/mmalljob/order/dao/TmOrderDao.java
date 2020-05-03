package com.zcoox.mmalljob.order.dao;

import com.zcoox.mmalljob.order.entity.TmOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface TmOrderDao {

    int deleteByPrimaryKey(Integer id);

    int insert(TmOrder record);

    int insertSelective(TmOrder record);

    TmOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmOrder record);

    int updateByPrimaryKey(TmOrder record);
}