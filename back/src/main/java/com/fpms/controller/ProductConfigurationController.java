package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dao.ProductConfigurationDao;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/23 22:45
 * @description: 配置中的产品逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductConfigurationController {

    @Autowired
    private ProductConfigurationDao productConfigurationDao;

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;
    /**
     * 通过产品标准库id和产品配置删除配置中某一产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/23 23:36
     * @param       param
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "删除配置中的产品")
    @DeleteMapping(value = "/configuration/production")
    public ResultBean<Boolean> deleteConfigurationProduction(@RequestBody Map<String,String> param){
        try {
            Integer productConfigId = Integer.valueOf(param.get("productConfigId"));
            Integer productStdId = Integer.valueOf(param.get("productStdId"));
            ProductConfiguration productConfiguration = productConfigurationDao.selectByPCIAndPSI(productConfigId,productStdId);
            productConfigurationDao.deleteByPrimaryKey(productConfiguration.getConfigurationId());
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 向配置中增加产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 16:38
     * @param       productConfiguration
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "增加配置中的产品")
    @PutMapping(value = "/configuration/production/actions/add")
    public ResultBean<Boolean> addConfigurationProduction(@RequestBody ProductConfiguration productConfiguration) {
        try {
            productLibraryConfigurationService.addConfigurationProduction(productConfiguration);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
