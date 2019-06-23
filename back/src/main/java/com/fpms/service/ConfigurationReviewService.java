package com.fpms.service;

import com.fpms.entity.ConfigurationReview;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:57
 * @description:
 * @modified :
 */
public interface ConfigurationReviewService {
    /**
     *  增加产品配置评论
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:46
     * @param       configurationReview
     * @return     : void
     */
    void addReview(ConfigurationReview configurationReview);
}
