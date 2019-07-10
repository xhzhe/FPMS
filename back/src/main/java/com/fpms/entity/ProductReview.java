package com.fpms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 产品评审实体类
 * @modified :
 */
public class ProductReview {
    private Integer productReviewId;

    private Integer productPreId;

    private Integer staffId;

    private String reviewDesc;

    private Date createTime;

    public Integer getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(Integer productReviewId) {
        this.productReviewId = productReviewId;
    }

    public Integer getProductPreId() {
        return productPreId;
    }

    public void setProductPreId(Integer productPreId) {
        this.productPreId = productPreId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc == null ? null : reviewDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}