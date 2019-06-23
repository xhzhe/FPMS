package com.fpms.controller;

import com.fpms.entity.ProductCategory;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:01
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 新增目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 9:37
     * @param       productCategory
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping("/productCategory")
    public ResultBean<Boolean> addProductCategory(@RequestBody ProductCategory productCategory){
        try{
            productCategoryService.addProductCategory(productCategory);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  修改目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 9:37
     * @param       productCategory
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping("/productCategory/{productCategoryId}")
    public ResultBean<Boolean> updateProductCategory(@RequestBody ProductCategory productCategory,@PathVariable Integer productCategoryId){
        try{
            productCategory.setCategoryId(productCategoryId);
            productCategoryService.updateProductCategory(productCategory);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  删除某个目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:14
     * @param       productCategory
     * @param       productCategoryId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping("/productCategory/{productCategoryId}")
    public  ResultBean<Boolean> delProductCategory(@RequestBody ProductCategory productCategory,@PathVariable Integer productCategoryId){
        try{
            productCategoryService.delProductCategory(productCategoryId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  获取所所目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:35
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductCategory>>
     */
    @GetMapping("/productCategorys")
    public  ResultBean<List<ProductCategory>> selectAllProductCategory(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try{
            productCategoryList = productCategoryService.selectAllProductCategory();
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productCategoryList);
    }

}
