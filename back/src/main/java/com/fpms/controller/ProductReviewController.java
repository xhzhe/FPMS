package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductReview;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:59
 * @description: 产品评审逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductLibraryPreService productLibraryPreService;
    /**
     *  新增对预选库产品的评估
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 11:28
     * @param       param
     * @param       productPreId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "新增产品评估")
    @PostMapping("/productPre/{productPreId}/review")
    public ResultBean<Boolean> addReview(@RequestBody Map<String,String> param, @PathVariable Integer productPreId){
        try{
            //添加评估
            if(param.get("staffId") == null || param.get("staffId").isEmpty()){
                return new ResultBean<>("未获取到员工id");
            }
            if(param.get("productStatus") == null || param.get("productStatus").isEmpty()){
                return new ResultBean<>("为获取到要设置的评审状态");
            }
            if(param.get("reviewDesc") == null || param.get("reviewDesc").isEmpty()){
                return new ResultBean<>("评审意见为空！");
            }
            if(param.get("productStatus").equals("0")){
                return new ResultBean<>("不能将评审状态设置为0");
            }
            Integer staffId = Integer.valueOf(param.get("staffId"));
            Byte productStatus = Byte.valueOf(param.get("productStatus"));
            ProductReview productReview = new ProductReview();
            productReview.setProductPreId(productPreId);
            productReview.setStaffId(staffId);
            productReview.setReviewDesc(param.get("reviewDesc"));
            productReviewService.addReview(productReview);
            //修改评估状态
            ProductLibraryPre productLibraryPre = new ProductLibraryPre();
            productLibraryPre.setProductPreId(productPreId);
            productLibraryPre.setProductStatus(productStatus);
            productLibraryPreService.modifyProduct(productLibraryPre);

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
