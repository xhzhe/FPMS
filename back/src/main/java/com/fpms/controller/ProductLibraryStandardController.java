package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            return res;
        }
        if(productStdId<0){
            setFail(res);
            return res;
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
    /**
     *  上架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 10:32
     * @param       productStdId
     * @return     : com.fpms.entity.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value="上架产品")
    @PostMapping("/productStd/{productStdId}")
    public ResultBean<Boolean> postProduct(@PathVariable Integer productStdId){
        ResultBean<Boolean> res = new ResultBean<>();
        if(productStdId==null){
            setFail(res);
            return res;
        }
        if(productStdId<0){
            setFail(res);
            return res;
        }
        try{
            boolean success=productLibraryStandardService.addProduct(productStdId);
            if(success){
                setSuccess("成功上架",success,res);
            }else {
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;
    }

}
