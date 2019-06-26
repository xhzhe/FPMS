package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:01
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryStandardController {
    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    public void setFail(ResultBean res){
        res.setData(null);
        res.setState(ResultBean.FAIL);
        res.setMsg(ResultBean.FAIL_MSG);
    }
    public <T> void setSuccess(String Message,T data,ResultBean res){
        res.setData(data);
        res.setState(ResultBean.SUCCESS);
        res.setMsg(Message);
    }
    /**
     *  下架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 10:58
     * @param       productStdId
     * @return     : com.fpms.entity.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "下架产品")
    @DeleteMapping("/productStd/{productStdId}")
    public ResultBean<Boolean> productObtained(@PathVariable Integer productStdId){
        ResultBean<Boolean> res = new ResultBean<>();
        if(productStdId==null){
            setFail(res);
        }
        if(productStdId<0){
            setFail(res);
        }
        try{
            boolean success=productLibraryStandardService.obetainProducts(productStdId);
            if(success){
                setSuccess("成功下架",success,res);
            }else {
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;

    }

}
