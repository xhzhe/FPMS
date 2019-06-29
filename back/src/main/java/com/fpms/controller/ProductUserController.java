package com.fpms.controller;

import com.fpms.entity.ProductUser;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/29 11:42
 * @description:用户产品库控制逻辑
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductUserController {
    @Autowired
    private ProductUserService productUserService;

    @GetMapping("/user/{userId}/myProducts")
    public ResultBean<List<ProductUser>> getMyProduct(@PathVariable Integer userId){
        List<ProductUser> productUserList ;
        try{
            productUserList = productUserService.selectByUserId(userId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productUserList);
    }
}
