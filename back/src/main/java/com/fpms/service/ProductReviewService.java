package com.fpms.service;

import com.fpms.entity.ProductReview;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:50
 * @description:
 * @modified   :
 */
public interface ProductReviewService {
    /**
     *  新增产品评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:33
     * @param       productReview
     * @return     : void
     */
    void addReview(ProductReview productReview);

    /**
     *  获取产品评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/24 11:34
     * @param       productPreId
     * @return     : com.fpms.entity.ProductReview
     */
    ProductReview selectByProductPreId(Integer productPreId);
}
