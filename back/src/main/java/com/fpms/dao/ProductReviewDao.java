package com.fpms.dao;

import com.fpms.entity.ProductReview;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductReviewDao {
    int deleteByPrimaryKey(Integer productReviewId);

    int insert(ProductReview record);

    int insertSelective(ProductReview record);

    ProductReview selectByPrimaryKey(Integer productReviewId);

    int updateByPrimaryKeySelective(ProductReview record);

    int updateByPrimaryKey(ProductReview record);
}