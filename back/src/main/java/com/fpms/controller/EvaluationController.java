package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.EvaluationDto;
import com.fpms.entity.*;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;

    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    /**
     *  获取用户对订单的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:10
     * @param       userId
     * @param       orderId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.Evaluation>
     */
    @GetMapping("/user/{userId}/order/{orderId}/evaluation")
    public ResultBean<EvaluationDto> selectEvaluationByUsearIdAndOrderId(@PathVariable Integer userId, @PathVariable Integer orderId){
        EvaluationDto evaluationDto = new EvaluationDto();
        try{
            Evaluation evaluation = evaluationService.selectEvaluationByUserIdAndOrderId(userId,orderId);
            User user = userService.getUserById(userId);
            evaluationDto.setUserName(user.getUserName());
            evaluationDto.setEvaluation(evaluation);
            if(evaluation.getEvaluationType() == 1){
                ProductLibraryPre productLibraryPre = productLibraryPreService.selectByStdId(evaluation.getProductStdId());
                evaluationDto.setProductLibraryPre(productLibraryPre);
            }else if(evaluation.getEvaluationType() == 2){
                ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(evaluation.getProductConId());
                evaluationDto.setProductLibraryConfiguration(productLibraryConfiguration);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationDto);
    }

    /**
     *  新增用户对订单的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 20:14
     * @param       evaluation
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "新增评价")
    @PostMapping("/user/{userId}/order/{orderId}/evaluation")
    public ResultBean<Boolean> updateEvaluation(@RequestBody Evaluation evaluation,@PathVariable Integer userId, @PathVariable Integer orderId ){
        Evaluation evaluation1 = evaluationService.selectEvaluationByUserIdAndOrderId(userId,orderId);
        if(evaluation1.getEvaluationStatus() == 1){
            return new ResultBean<>("已评价，请勿重复评价！");
        }
        try{
            //添加评价
            evaluation.setEvaluationId(evaluation1.getEvaluationId());
            evaluation.setEvaluationStatus(Byte.valueOf("1"));
            evaluationService.updateEvaluationByEvaluationId(evaluation);
            //修改产品分数
            Order order = orderService.selectOrderByOrderId(orderId);
            if(order.getOrderType() == 1){
                ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order.getProductStdId());
                double old_avg_score = productLibraryStandard.getEvalutionAvgScore().doubleValue();
                int old_num = productLibraryStandard.getEvalutionNum().intValue();
                int score = evaluation.getEvaluationScore().intValue();
                //计算新的平均分
                double new_avg_score = (old_avg_score * old_num + score)/(old_num + 1);
                productLibraryStandard.setEvalutionAvgScore(BigDecimal.valueOf(new_avg_score));
                productLibraryStandard.setEvalutionNum(old_num+1);
                productLibraryStandardService.updateProductStandard(productLibraryStandard);
            }else if(order.getOrderType() == 2){
                ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(order.getProductConId());
                double old_avg_score = productLibraryConfiguration.getEvalutionAvgScore().doubleValue();
                int old_num = productLibraryConfiguration.getEvalutionNum().intValue();
                int score = evaluation.getEvaluationScore().intValue();
                //计算新的平均分
                double new_avg_score = (old_avg_score * old_num + score)/(old_num + 1);
                productLibraryConfiguration.setEvalutionAvgScore(BigDecimal.valueOf(new_avg_score));
                productLibraryConfiguration.setEvalutionNum(old_num+1);
                productLibraryConfigurationService.updateProductConfiguration(productLibraryConfiguration);
            }
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
    public ResultBean<List<EvaluationDto>> selectAllEvaluationByUserId(@PathVariable Integer userId){
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();
        try{
            List<Evaluation> evaluationList = evaluationService.selectAllEvaluationByUserId(userId);
            User user = userService.getUserById(userId);
            for(int i=0;i<evaluationList.size();i++){
                Evaluation evaluation = evaluationList.get(i);
                EvaluationDto evaluationDto = new EvaluationDto();
                evaluationDto.setEvaluation(evaluation);
                evaluationDto.setUserName(user.getUserName());
                if(evaluation.getEvaluationType() == 1){
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectByStdId(evaluation.getProductStdId());
                    evaluationDto.setProductLibraryPre(productLibraryPre);
                }else if(evaluation.getEvaluationType() == 2){
                    ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(evaluation.getProductConId());
                    evaluationDto.setProductLibraryConfiguration(productLibraryConfiguration);
                }
                evaluationDtoList.add(evaluationDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationDtoList);
    }

    /**
     *  获取标准产品的所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:07
     * @param       productStdId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/productStd/{productStdId}/evaluations")
    public ResultBean<List<EvaluationDto>> selectAllEvaluationByProductStdId(@PathVariable Integer productStdId){
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();
        try{
            List<Evaluation> evaluationList = evaluationService.selectAllEvaluationByProductStdId(productStdId);
            for(int i=0;i<evaluationList.size();i++){
                Evaluation evaluation = evaluationList.get(i);
                EvaluationDto evaluationDto = new EvaluationDto();
                User user = userService.getUserById(evaluation.getUserId());
                evaluationDto.setEvaluation(evaluation);
                evaluationDto.setUserName(user.getUserName());
                ProductLibraryPre productLibraryPre = productLibraryPreService.selectByStdId(evaluation.getProductStdId());
                evaluationDto.setProductLibraryPre(productLibraryPre);
                evaluationDtoList.add(evaluationDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationDtoList);
    }

    /**
     *  获取配置产品的所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 21:07
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/productCon/{productConId}/evaluations")
    public ResultBean<List<EvaluationDto>> selectAllEvaluationByProductConId(@PathVariable Integer productConId){
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();
        try{
            List<Evaluation> evaluationList = evaluationService.selectAllEvaluationByProductConId(productConId);
            for(int i=0;i<evaluationList.size();i++){
                Evaluation evaluation = evaluationList.get(i);
                User user = userService.getUserById(evaluation.getUserId());
                EvaluationDto evaluationDto = new EvaluationDto();
                evaluationDto.setUserName(user.getUserName());
                evaluationDto.setEvaluation(evaluation);
                ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(evaluation.getProductConId());
                evaluationDto.setProductLibraryConfiguration(productLibraryConfiguration);
                evaluationDtoList.add(evaluationDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationDtoList);
    }

    /**
     *  获取所有评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 15:52
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Evaluation>>
     */
    @GetMapping("/evaluations")
    public ResultBean<List<EvaluationDto>> selectAllEvaluation(){
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();
        try{
            List<Evaluation> evaluationList = evaluationService.selectAll();
            for(int i=0;i<evaluationList.size();i++){
                Evaluation evaluation = evaluationList.get(i);
                User user = userService.getUserById(evaluation.getUserId());
                EvaluationDto evaluationDto = new EvaluationDto();
                evaluationDto.setUserName(user.getUserName());
                evaluationDto.setEvaluation(evaluation);
                if(evaluation.getEvaluationType() == 1){
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectByStdId(evaluation.getProductStdId());
                    evaluationDto.setProductLibraryPre(productLibraryPre);
                }else if(evaluation.getEvaluationType() == 2){
                    ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(evaluation.getProductConId());
                    evaluationDto.setProductLibraryConfiguration(productLibraryConfiguration);
                }
                evaluationDtoList.add(evaluationDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(evaluationDtoList);
    }

    /**
     *  通过evluationId删除评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 10:22
     * @param       evaluationId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping("/evaluation/{evaluationId}")
    public ResultBean<Boolean> delEvalutionById(@PathVariable Integer evaluationId){
        try{
            evaluationService.delEvaluationByEvaluationId(evaluationId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
