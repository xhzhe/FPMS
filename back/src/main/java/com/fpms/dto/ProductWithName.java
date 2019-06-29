package com.fpms.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : xuhuizhe
 * @date : 2019/6/29 14:36
 * @description:
 * @modified :
 */
public class ProductWithName {
    private Integer productStdId;

    private Integer productPreId;

    private Byte suitUser;

    private Byte interRiskRating;

    private Byte exchangeRateRiskIndex;

    private Byte interestRateRiskIndex;

    public Integer getProductStdId() {
        return productStdId;
    }

    public void setProductStdId(Integer productStdId) {
        this.productStdId = productStdId;
    }

    public Integer getProductPreId() {
        return productPreId;
    }

    public void setProductPreId(Integer productPreId) {
        this.productPreId = productPreId;
    }

    public Byte getSuitUser() {
        return suitUser;
    }

    public void setSuitUser(Byte suitUser) {
        this.suitUser = suitUser;
    }

    public Byte getInterRiskRating() {
        return interRiskRating;
    }

    public void setInterRiskRating(Byte interRiskRating) {
        this.interRiskRating = interRiskRating;
    }

    public Byte getExchangeRateRiskIndex() {
        return exchangeRateRiskIndex;
    }

    public void setExchangeRateRiskIndex(Byte exchangeRateRiskIndex) {
        this.exchangeRateRiskIndex = exchangeRateRiskIndex;
    }

    public Byte getInterestRateRiskIndex() {
        return interestRateRiskIndex;
    }

    public void setInterestRateRiskIndex(Byte interestRateRiskIndex) {
        this.interestRateRiskIndex = interestRateRiskIndex;
    }

    public Byte getMarketRiskIndex() {
        return marketRiskIndex;
    }

    public void setMarketRiskIndex(Byte marketRiskIndex) {
        this.marketRiskIndex = marketRiskIndex;
    }

    public Byte getCreditRiskIndex() {
        return creditRiskIndex;
    }

    public void setCreditRiskIndex(Byte creditRiskIndex) {
        this.creditRiskIndex = creditRiskIndex;
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

    private Byte marketRiskIndex;

    private Byte creditRiskIndex;

    private Byte isSale;

    private Date saleStartTime;

    private Date saleEndTime;

    private Integer saleNum;

    private Integer stock;

    private BigDecimal evalutionAvgScore;

    private Integer evalutionNum;

    private Date createTime;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
