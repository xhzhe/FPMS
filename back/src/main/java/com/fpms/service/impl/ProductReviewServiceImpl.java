package com.fpms.service.impl;

import com.fpms.dao.ProductReviewDao;
import com.fpms.entity.ProductReview;
import com.fpms.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:13
 * @description:
 * @modified :
 */
@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewDao productReviewDao;

    @Override
    public void addReview(ProductReview productReview) {
        productReviewDao.insertSelective(productReview);
    }

    @Override
    public ProductReview selectByProductPreId(Integer productPreId) {
        return productReviewDao.selectByProductPreId(productPreId);
    }
}
