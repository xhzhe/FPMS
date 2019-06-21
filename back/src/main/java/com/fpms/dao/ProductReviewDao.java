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

    /**
     *  通过主键删除一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:16
     * @param       productReviewId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer productReviewId);

    /**
     *  插入一条记录的全部字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:16
     * @param       record
     * @return     : int
     */
    int insert(ProductReview record);

    /**
     *  插入一条记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:16
     * @param       record
     * @return     : int
     */
    int insertSelective(ProductReview record);

    /**
     *  通过主键查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:16
     * @param       productReviewId
     * @return     : com.fpms.entity.ProductReview
     */
    ProductReview selectByPrimaryKey(Integer productReviewId);

    /**
     *  通过主键更新记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:17
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(ProductReview record);

    /**
     *  通过主键更新记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 14:17
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(ProductReview record);
}