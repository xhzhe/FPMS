package com.fpms.service;

import com.fpms.entity.Evaluation;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:57
 * @description:
 * @modified :
 */
public interface EvaluationService {
    /**
     *  通过userId和orderId找到对应的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 17:08
     * @param       userId
     * @param       orderId
     * @return     : void
     */
    Evaluation selectEvaluationByUserIdAndOrderId(Integer userId, Integer orderId);

    /**
     *  通过evalutionId更新评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:16
     * @param       evaluation
     * @return     : void
     */
    void updateEvaluationByEvaluationId(Evaluation evaluation);

    /**
     *  通过evalutionId设置评价的isDelete字段为1从而达到删除的效果
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:30
     * @param       evaluationId
     * @return     : void
     */
    void delEvaluationByEvaluationId(Integer evaluationId);

    /**
     *  添加评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:41
     * @param       evaluation
     * @return     : void
     */
    void addEvaluation(Evaluation evaluation);

    /**
     *  通过userId查找评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:45
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllEvaluationByUserId(Integer userId);

    /**
     *  通过productStdId查找评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:00
     * @param       productStdId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllEvaluationByProductStdId(Integer productStdId);

    /**
     *  通过productConId查找评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:04
     * @param       productConId
     * @return     : java.util.List<com.fpms.entity.Evaluation>
     */
    List<Evaluation> selectAllEvaluationByProductConId(Integer productConId);
}
