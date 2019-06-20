package com.fpms.dao;

import com.fpms.entity.Evaluation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 评价表数据操作类
 * @modified :
 */
@Component
public interface EvaluationDao {
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:12
     * @param       evaluationId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer evaluationId);

    /**
     *  插入所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:13
     * @param       record
     * @return     : int
     */
    int insert(Evaluation record);

    /**
     *  只插入不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:13
     * @param       record
     * @return     : int
     */
    int insertSelective(Evaluation record);

    /**
     *  通过主键查找一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:13
     * @param       evaluationId
     * @return     : com.fpms.entity.Evaluation
     */
    Evaluation selectByPrimaryKey(Integer evaluationId);

    /**
     *  通过主键更新不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:13
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(Evaluation record);

    /**
     *  通过主键更新所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:14
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(Evaluation record);

    /**
     *  通过userId和OrderId查询一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:14
     * @param       userId
     * @param       orderId
     * @return     : com.fpms.entity.Evaluation
     */
    Evaluation selectByUserIdAndOrderId(Integer userId, Integer orderId);

    /**
     *  通过userId查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:14
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllByUserId(Integer userId);

    /**
     *  通过productStdId查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:15
     * @param       productStdId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllByProductStdId(Integer productStdId);

    /**
     *  通过productConId查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:15
     * @param       productConId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllByProductConId(Integer productConId);
}