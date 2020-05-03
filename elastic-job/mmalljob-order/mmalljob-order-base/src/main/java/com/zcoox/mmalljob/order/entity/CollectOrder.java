package com.zcoox.mmalljob.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采集汇总订单
 */
public class CollectOrder implements Serializable {

    private static final long serialVersionUID = 558881762569226125L;

    private Integer id;

    private Integer thirdOrderId;

    private Integer type;

    private BigDecimal totalAmount;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer delState;

    public CollectOrder(Integer id, Integer thirdOrderId, Integer type, BigDecimal totalAmount, String createUser, Date createTime, String updateUser, Date updateTime, Integer delState) {
        this.id = id;
        this.thirdOrderId = thirdOrderId;
        this.type = type;
        this.totalAmount = totalAmount;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.delState = delState;
    }

    public CollectOrder() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(Integer thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
}