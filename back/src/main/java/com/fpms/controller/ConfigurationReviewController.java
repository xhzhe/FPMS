package com.fpms.controller;

import com.fpms.entity.ConfigurationReview;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ConfigurationReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ConfigurationReviewController {
    @Autowired
    private ConfigurationReviewService configurationReviewService;

    /**
     *  新增对配置产品的评价
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:50
     * @param       configurationReview
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping("/productCon/{productConId}/review")
    public ResultBean<Boolean> addReview(@RequestBody ConfigurationReview configurationReview, @PathVariable Integer productConId){
        try{
            configurationReview.setProductConId(productConId);
            configurationReviewService.addReview(configurationReview);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
