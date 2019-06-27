package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.EvaluationDto;
import com.fpms.entity.Evaluation;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.EvaluationService;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
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

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;
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
    public ResultBean<List<EvaluationDto>> selectAllEvaluationByUserId(@PathVariable Integer userId){
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();
        try{
            List<Evaluation> evaluationList = evaluationService.selectAllEvaluationByUserId(userId);
            for(int i=0;i<evaluationList.size();i++){
                Evaluation evaluation = evaluationList.get(i);
                EvaluationDto evaluationDto = new EvaluationDto();
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
                evaluationDto.setEvaluation(evaluation);
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
                EvaluationDto evaluationDto = new EvaluationDto();
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
                EvaluationDto evaluationDto = new EvaluationDto();
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
}
