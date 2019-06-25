package com.fpms.controller;

import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置库逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryConfigurationController {

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/21 14:55
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List>
     */
    @GetMapping(value = "/configurations")
    public ResultBean<List> getAllConfiguration(){
        try{
            return new ResultBean<>(productLibraryConfigurationService.getAllConfiguration());
        }catch (Exception e){
            return new ResultBean<>(e.getMessage());
        }
    }

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 21:20
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping(value = "/configurations")
    public ResultBean<Boolean> deleteConfiguration(@RequestParam("productConId") Integer productConId){
        try {
            productLibraryConfigurationService.deleteConfiguration(productConId);
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
    /**
     *  获取未评估的配置产品列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 14:01
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryConfiguration>>
     */
    @GetMapping("/unReviewProductCons")
    public  ResultBean<List<ProductLibraryConfiguration>> getUnReviewProductCon(){
        List<ProductLibraryConfiguration> unReviewProductList = new ArrayList<>();
        try{
            unReviewProductList = productLibraryConfigurationService.getUnReviewProductList();
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }
}
