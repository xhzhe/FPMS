package com.fpms.dao;

import com.fpms.entity.ConfigurationReview;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ConfigurationReviewDao {
    int deleteByPrimaryKey(Integer conReviewId);

    int insert(ConfigurationReview record);

    int insertSelective(ConfigurationReview record);

    ConfigurationReview selectByPrimaryKey(Integer conReviewId);

    int updateByPrimaryKeySelective(ConfigurationReview record);

    int updateByPrimaryKey(ConfigurationReview record);
}