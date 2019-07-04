package com.fpms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 用户产品实体类
 * @modified :
 */
public class ProductUser {
    private Integer productUserId;

    private Integer userId;

    private Integer productStdId;

    private Integer productConId;

    private Byte productUserType;

    private BigDecimal payMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date termDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date createTime;

    public Integer getProductUserId() {
        return productUserId;
    }

    public void setProductUserId(Integer productUserId) {
        this.productUserId = productUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductStdId() {
        return productStdId;
    }

    public void setProductStdId(Integer productStdId) {
        this.productStdId = productStdId;
    }

    public Integer getProductConId() {
        return productConId;
    }

    public void setProductConId(Integer productConId) {
        this.productConId = productConId;
    }

    public Byte getProductUserType() {
        return productUserType;
    }

    public void setProductUserType(Byte productUserType) {
        this.productUserType = productUserType;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}