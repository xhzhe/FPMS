package com.fpms.dto;

import java.math.BigDecimal;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/20 14:54
 * @description: 封装配置的信息
 * @modified :
 */
public class ProductLibraryConfigurationDto {

    private Integer productConId;

    private String productConName;

    private Integer productConNum;

    private String productConDesc;

    private BigDecimal productConPrice;

    private Byte reviewStatus;

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
        this.productConName = productConName;
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
        this.productConDesc = productConDesc;
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
}
