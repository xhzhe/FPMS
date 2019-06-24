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
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:52
     * @param       conReviewId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer conReviewId);

    /**
     *  插入一条记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:52
     * @param       record
     * @return     : int
     */
    int insert(ConfigurationReview record);

    /**
     *  插入一条记录中的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:52
     * @param       record
     * @return     : int
     */
    int insertSelective(ConfigurationReview record);

    /**
     *  通过主键查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:52
     * @param       conReviewId
     * @return     : com.fpms.entity.ConfigurationReview
     */
    ConfigurationReview selectByPrimaryKey(Integer conReviewId);

    /**
     *  通过主键更新一条记录中的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:53
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(ConfigurationReview record);

    /**
     *  通过主键更新一条记录的全部字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:53
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(ConfigurationReview record);

    /**
     *  通过ProductConId查询一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/24 11:21
     * @param       productConId
     * @return     : com.fpms.entity.ConfigurationReview
     */
    ConfigurationReview selectByProductConId(Integer productConId);
}