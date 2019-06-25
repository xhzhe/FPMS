package com.fpms.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : TianHong Liao
 * @date : 2019/6/25 11:43
 * @description:订单Dto
 * @modified :
 */
public class OrderDto {
    private Integer orderId;
    private String userName;
    private BigDecimal orderMoney;
    private Byte orderStatus;
    private Date createTime;
    private String productName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
