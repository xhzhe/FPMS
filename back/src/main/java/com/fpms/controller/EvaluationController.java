package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.Evaluation;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description:评价逻辑控制类
 * @modified :
 */
@RestController
@CrossOrigin
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    /**
     *  获取用户对订单的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:10
     * @param       userId
     * @param       orderId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.Evaluation>
     */
    @GetMapping("/user/{userId}/order/{orderId}/evaluation")
    public ResultBean<Evaluation> selectEvaluationByUsearIdAndOrderId(@PathVariable Integer userId, @PathVariable Integer orderId){
        Evaluation evaluation = new Evaluation();
        try{
            evaluation = evaluationService.selectEvaluationByUserIdAndOrderId(userId,orderId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluation);
    }

    /**
     *  修改用户对订单的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:14
     * @param       evaluation
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "更新评价")
    @PutMapping("/user/{userId}/order/{orderId}/evaluation/{evaluationId}")
    public ResultBean<Boolean> updateEvaluation(@RequestBody Evaluation evaluation,@PathVariable Integer userId, @PathVariable Integer orderId,@PathVariable Integer evaluationId ){
        try{
            evaluation.setEvaluationId(evaluationId);
//            evaluation.setOrderId(orderId);
//            evaluation.setUserId(userId);
            evaluationService.updateEvaluationByEvaluationId(evaluation);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
    /**
     *  删除评价：实际上数据库不实际删除 只是将isDelete设置为1
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:28
     * @param       userId
     * @param       orderId
     * @param       evaluationId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping("/user/{userId}/order/{orderId}/evaluation/{evaluationId}")
    public ResultBean<Boolean> delEvaluation(@PathVariable Integer userId, @PathVariable Integer orderId,@PathVariable Integer evaluationId ){
        try{
            evaluationService.delEvaluationByEvaluationId(evaluationId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  获取用户的所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:06
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/user/{userId}/evaluations")
    public ResultBean<List<Evaluation>> selectAllEvaluationByUserId(@PathVariable Integer userId){
        List<Evaluation> evaluationList = new ArrayList<>();
        try{
            evaluationList = evaluationService.selectAllEvaluationByUserId(userId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationList);
    }

    /**
     *  获取标准产品的所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:07
     * @param       productStdId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/productStd/{productStdId}/evaluations")
    public ResultBean<List<Evaluation>> selectAllEvaluationByProductStdId(@PathVariable Integer productStdId){
        List<Evaluation> evaluationList = new ArrayList<>();
        try{
            evaluationList = evaluationService.selectAllEvaluationByProductStdId(productStdId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationList);
    }

    /**
     *  获取配置产品的所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:07
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/productCon/{productConId}/evaluations")
    public ResultBean<List<Evaluation>> selectAllEvaluationByProductConId(@PathVariable Integer productConId){
        List<Evaluation> evaluationList = new ArrayList<>();
        try{
            evaluationList = evaluationService.selectAllEvaluationByProductConId(productConId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationList);
    }
}
