package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.ConfigurationDto;
import com.fpms.dto.ProductLibraryConfigurationDto;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
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

    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 16:05
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryConfiguration>>
     */
    @GetMapping(value = "/configurations")
    public ResultBean<List<ConfigurationDto>> getAllConfiguration(){
        ArrayList<ConfigurationDto> configurationDtoArrayList = new ArrayList<>();
        try{
            List<ProductLibraryConfiguration> productLibraryConfigurationList = productLibraryConfigurationService.getAllConfiguration();
            for(int i = 0; i < productLibraryConfigurationList.size();i++){
                ConfigurationDto configurationDto = new ConfigurationDto();
                configurationDto.setProductLibraryConfiguration(productLibraryConfigurationList.get(i));
                List<ProductConfiguration> productConfigurationList = productLibraryConfigurationService.getProductConfigurationByproductConId(productLibraryConfigurationList.get(i).getProductConId());
                ArrayList<ProductLibraryStandard> productLibraryStandardArrayList = new ArrayList<>();
                ArrayList<ProductLibraryPre> productLibraryPreArrayList = new ArrayList<>();
                for(int j = 0; j < productConfigurationList.size(); j++){
                    Integer productStdId = productConfigurationList.get(i).getProductStdId();
                    ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(productStdId);
                    Integer productPreId = productLibraryStandard.getProductPreId();
                    productLibraryStandardArrayList.add(productLibraryStandard);
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
                    productLibraryPreArrayList.add(productLibraryPre);
                }
                configurationDto.setProductLibraryPreList(productLibraryPreArrayList);
                configurationDto.setProductLibraryStandardList(productLibraryStandardArrayList);
                configurationDtoArrayList.add(configurationDto);
            }
        }catch (Exception e){
            return new ResultBean<>(e.getMessage());
        }
        return new ResultBean<>(configurationDtoArrayList);
    }

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 21:20
     * @param       productConId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "删除配置")
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
    public  ResultBean<List<ProductLibraryConfigurationDto>> getUnReviewProductCon(){
        List<ProductLibraryConfigurationDto> unReviewProductList = new ArrayList<>();
        try{
            unReviewProductList = productLibraryConfigurationService.getUnReviewProductList();
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }


}
