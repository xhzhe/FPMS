package com.fpms.dao;

import com.fpms.entity.ConfigurationReview;

public interface ConfigurationReviewDao {
    int deleteByPrimaryKey(Integer conReviewId);

    int insert(ConfigurationReview record);

    int insertSelective(ConfigurationReview record);

    ConfigurationReview selectByPrimaryKey(Integer conReviewId);

    int updateByPrimaryKeySelective(ConfigurationReview record);

    int updateByPrimaryKey(ConfigurationReview record);
}