package com.fpms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 产品配置库实体类
 * @modified :
 */
public class ProductLibraryConfiguration {
    private Integer productConId;

    private String productConName;

    private Integer productConNum;

    private String productConDesc;

    private BigDecimal productConPrice;

    private Byte reviewStatus;

    private Byte isSale;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date saleStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date saleEndTime;

    private Integer saleNum;

    private Integer stock;

    private BigDecimal evalutionAvgScore;

    private Integer evalutionNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+0")
    private Date createTime;

    public Integer getProductConId() {
        return productConId;
    }

    public void setProductConId(Integer productConId) {
        this.productConId = productConId;
    }

    public String getProductConName() {
        return productConName;
    }

    public void setProductConName(String productConName) {
        this.productConName = productConName == null ? null : productConName.trim();
    }

    public Integer getProductConNum() {
        return productConNum;
    }

    public void setProductConNum(Integer productConNum) {
        this.productConNum = productConNum;
    }

    public String getProductConDesc() {
        return productConDesc;
    }

    public void setProductConDesc(String productConDesc) {
        this.productConDesc = productConDesc == null ? null : productConDesc.trim();
    }

    public BigDecimal getProductConPrice() {
        return productConPrice;
    }

    public void setProductConPrice(BigDecimal productConPrice) {
        this.productConPrice = productConPrice;
    }

    public Byte getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Byte getIsSale() {
        return isSale;
    }

    public void setIsSale(Byte isSale) {
        this.isSale = isSale;
    }

    public Date getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(Date saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getEvalutionAvgScore() {
        return evalutionAvgScore;
    }

    public void setEvalutionAvgScore(BigDecimal evalutionAvgScore) {
        this.evalutionAvgScore = evalutionAvgScore;
    }

    public Integer getEvalutionNum() {
        return evalutionNum;
    }

    public void setEvalutionNum(Integer evalutionNum) {
        this.evalutionNum = evalutionNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}