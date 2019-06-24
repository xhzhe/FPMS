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
     *  增加对配置产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:46
     * @param       configurationReview
     * @return     : void
     */
    void addReview(ConfigurationReview configurationReview);

    /**
     *  获取该产品配置的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/24 11:18
     * @param       productConId
     * @return     : com.fpms.entity.ConfigurationReview
     */
    ConfigurationReview selectByProductConId(Integer productConId);
}
