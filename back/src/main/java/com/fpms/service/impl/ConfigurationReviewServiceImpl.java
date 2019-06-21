package com.fpms.service.impl;

import com.fpms.dao.ConfigurationReviewDao;
import com.fpms.entity.ConfigurationReview;
import com.fpms.service.ConfigurationReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:15
 * @description:
 * @modified :
 */
@Service
public class ConfigurationReviewServiceImpl implements ConfigurationReviewService {
    @Autowired
    private ConfigurationReviewDao configurationReviewDao;

    @Override
    public void addReview(ConfigurationReview configurationReview) {
        configurationReviewDao.insertSelective(configurationReview);
    }
}
