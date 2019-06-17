package com.fpms.dao;

import com.fpms.entity.ProductReview;

public interface ProductReviewDao {
    int deleteByPrimaryKey(Integer productReviewId);

    int insert(ProductReview record);

    int insertSelective(ProductReview record);

    ProductReview selectByPrimaryKey(Integer productReviewId);

    int updateByPrimaryKeySelective(ProductReview record);

    int updateByPrimaryKey(ProductReview record);
}