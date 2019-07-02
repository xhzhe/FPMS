package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
//    @Autowired
    /**
     *  预选库服务
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/2 9:57
     */
    private ProductLibraryPreService productLibraryPreService;
    @Autowired
    public ProductLibraryPreController(ProductLibraryPreService productLibraryPreService){
        this.productLibraryPreService=productLibraryPreService;
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
                throw new Exception("缺少ID");
            }
            productLibraryPreService.modifyProduct(product);
            res.setData(true);
        }catch (Exception e){
            return new ResultBean<>(e);
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
            productLibraryPreService.addProduct(product);
            res.setData(true);
        }catch (Exception e){
            return new ResultBean<>(e);
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
        List<ProductLibraryPre> unReviewProductList;
        try{
            unReviewProductList = productLibraryPreService.getUnReviewProductList();
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }

    /**
     * 获取预选库的所有产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 11:46
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryPre>>
     */
    @GetMapping(value = "/productPres")
    public ResultBean<List<ProductLibraryPre>> getAllProductPres(){
        List<ProductLibraryPre> productLibraryPres;
        try {
            productLibraryPres = productLibraryPreService.getAllProductPres();
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productLibraryPres);
    }

    /**
     *  查找预选库产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/2 15:57
     * @param       productPreId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.ProductLibraryPre>
     */
    @OperationLog("按id查找预选库产品")
    @GetMapping("/productPre/{productPreId}")
    public ResultBean<ProductLibraryPre> getProductPre(@PathVariable Integer productPreId){
        try{
            ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
            ResultBean<ProductLibraryPre> res = new ResultBean<>();
            res.setData(productLibraryPre);
            return res;
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }
}
