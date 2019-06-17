package com.fpms.entity;

import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 评价实体类
 * @modified :
 */
public class Evaluation {
    private Integer evaluationId;

    private Integer orderId;

    private Integer userId;

    private Integer productStdId;

    private Integer productConId;

    private Integer evaluationScore;

    private String evaluationDesc;

    private Byte evaluationType;

    private Byte evaluationStatus;

    private Byte isDelete;

    private Date createTime;

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Integer evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public String getEvaluationDesc() {
        return evaluationDesc;
    }

    public void setEvaluationDesc(String evaluationDesc) {
        this.evaluationDesc = evaluationDesc == null ? null : evaluationDesc.trim();
    }

    public Byte getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Byte evaluationType) {
        this.evaluationType = evaluationType;
    }

    public Byte getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(Byte evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}