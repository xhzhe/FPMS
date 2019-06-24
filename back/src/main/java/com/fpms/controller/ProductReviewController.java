package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductReview;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:59
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;

    /**
     *  新增对预选库产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 11:49
     * @param       productReview
     * @param       productPreId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping("/productPre/{productPreId}/review")
    public ResultBean<Boolean> addReview(@RequestBody ProductReview productReview,@PathVariable Integer productPreId){
        try{
            productReview.setProductPreId(productPreId);
            productReviewService.addReview(productReview);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  获取对预选库产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/24 11:35
     * @param       productPreId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.ProductReview>
     */
    @GetMapping("/productPre/{productPreId}/review")
    public ResultBean<ProductReview> selectReviewByProductPreId(@PathVariable Integer productPreId){
        ProductReview productReview = new ProductReview();
        try{
            productReview = productReviewService.selectByProductPreId(productPreId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productReview);
    }
}
