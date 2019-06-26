package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 14:59
 * @description: 产品预选库逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryPreController {
    @Autowired
    private ProductLibraryPreService productLibraryPreService;
    private void setFail(@NotNull ResultBean res){
        res.setData(null);
        res.setState(ResultBean.FAIL);
        res.setMsg(ResultBean.FAIL_MSG);
    }
    private  <T> void setSuccess(String message,T data,@NotNull ResultBean res){
        res.setData(data);
        res.setState(ResultBean.SUCCESS);
        res.setMsg(message);
    }
    /**
     *  修改产品属性
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 10:59
     * @param       product
     * @return     : com.fpms.entity.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "修改产品属性")
    @PutMapping("/product")
    public ResultBean<Boolean> modifyProduct(@RequestBody ProductLibraryPre product){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            if(product.getProductPreId()==null){
                setFail(res);
                throw new Exception("缺少ID");
            }
            boolean success=productLibraryPreService.modifyProduct(product);
            if(success){
                setSuccess(ResultBean.SUCC_MSG,success,res);
            }else{
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;
    }
    /**
     *  上传商品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 10:59
     * @param       product
     * @return     : com.fpms.entity.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "新增预选库产品")
    @PostMapping("/productPre")
    public ResultBean<Boolean> addProduct(@RequestBody ProductLibraryPre product){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            boolean success=productLibraryPreService.addProduct(product);
            if(success){
                setSuccess(ResultBean.SUCC_MSG,success,res);
            }else{
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;
    }

    /**
     *  获取未评估的预选库产品列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 13:03
     * @param
     * @return     : com.fpms.entity.ResultBean<java.util.List<com.fpms.entity.ProductLibraryPre>>
     */
    @GetMapping("/unReviewProductPres")
    public  ResultBean<List<ProductLibraryPre>> getUnReviewProductPre(){
        List<ProductLibraryPre> unReviewProductList = new ArrayList<>();
        try{
            unReviewProductList = productLibraryPreService.getUnReviewProductList();
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }
}
