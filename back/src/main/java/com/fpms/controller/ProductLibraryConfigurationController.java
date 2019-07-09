package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.*;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 获取所有配置的信息以及其包含的产品
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryConfiguration>>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 16:05
     */
    @GetMapping(value = "/configurations")
    public ResultBean<List<ConWithProNameDto>> getAllConfiguration() {
        ArrayList<ConWithProNameDto> conWithProNameDtoArrayList = new ArrayList<>();
        try {
            List<ProductLibraryConfiguration> productLibraryConfigurationList = productLibraryConfigurationService.getAllConfiguration();
            for (int i = 0; i < productLibraryConfigurationList.size(); i++) {
                ConWithProNameDto conWithProNameDto = new ConWithProNameDto();
                conWithProNameDto.setProductLibraryConfiguration(productLibraryConfigurationList.get(i));
                List<ProductConfiguration> productConfigurationList = productLibraryConfigurationService.getProductConfigurationByproductConId(productLibraryConfigurationList.get(i).getProductConId());
                if (productConfigurationList != null) {
                    ArrayList<ProductLibraryStandardWithName> productLibraryStandardWithNameArrayList = new ArrayList<>();
                    ArrayList<ProductLibraryPre> productLibraryPreArrayList = new ArrayList<>();
                    for (int j = 0; j < productConfigurationList.size(); j++) {
                        ProductLibraryStandardWithName productLibraryStandardWithName = new ProductLibraryStandardWithName();
                        Integer productStdId = productConfigurationList.get(j).getProductStdId();
                        productLibraryStandardWithName.setProductLibraryStandard(productLibraryStandardService.selectById(productStdId));
                        Integer productPreId = productLibraryStandardWithName.getProductLibraryStandard().getProductPreId();
                        ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
                        productLibraryStandardWithName.setProductName(productLibraryPre.getProductName());
                        productLibraryStandardWithNameArrayList.add(productLibraryStandardWithName);
                        productLibraryPreArrayList.add(productLibraryPre);
                    }
                    conWithProNameDto.setProductLibraryPreList(productLibraryPreArrayList);
                    conWithProNameDto.setProductLibraryStandardWithNameList(productLibraryStandardWithNameArrayList);
                    conWithProNameDtoArrayList.add(conWithProNameDto);
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            return new ResultBean<>(e.getMessage());
        }
        return new ResultBean<>(conWithProNameDtoArrayList);
    }

    /**
     * 通过配置id删除配置
     *
     * @param productConId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 21:20
     */
    @OperationLog(value = "删除配置")
    @DeleteMapping(value = "/configurations")
    @Transactional(rollbackFor = Exception.class)
    public ResultBean<Boolean> deleteConfiguration(@RequestBody Map productConId) {
        try {
            if (!productConId.containsKey("productConId")) {
                throw new Exception("id为空");
            }
            productLibraryConfigurationService.deleteConfiguration((Integer) productConId.get("productConId"));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 获取未评估的配置产品列表
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryConfiguration>>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 14:01
     */
    @GetMapping("/unReviewProductCons")
    public ResultBean<List<ProductLibraryConfigurationDto>> getUnReviewProductCon() {
        List<ProductLibraryConfigurationDto> unReviewProductList = new ArrayList<>();
        try {
            unReviewProductList = productLibraryConfigurationService.getUnReviewProductList();
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }

    /**
     * 通过productConId获取配置的所有产品的名字和比例
     *
     * @param productConId
     * @return : com.fpms.entity.pojo.ResultBean<com.fpms.dto.ConProDto>
     * @author ：YongBiao Liaocon
     * @date ：Created in 2019/6/28 10:05
     */
    @GetMapping(value = "/configuration/{productConId}")
    public ResultBean<ConProDto> getConfiguration(@PathVariable Integer productConId) {
        ConProDto conProDto = new ConProDto();
        try {
            ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(productConId);
            List<ProductConfiguration> productConfigurationList = productLibraryConfigurationService.getProductConfigurationByproductConId(productConId);
            if (productConfigurationList == null) {
                return new ResultBean<>("该配置没有产品");
            } else {
                List<String> productName = new ArrayList<>();
                List<BigDecimal> productPercentage = new ArrayList<>();
                List<Integer> productStdIds = new ArrayList<>();
                List<Integer> productPreIds = new ArrayList<>();
                for (int i = 0; i < productConfigurationList.size(); i++) {
                    Integer productStdId = productConfigurationList.get(i).getProductStdId();
                    ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(productStdId);
                    Integer productPreId = productLibraryStandard.getProductPreId();
                    productPreIds.add(productPreId);
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
                    productName.add(productLibraryPre.getProductName());
                    productPercentage.add(productConfigurationList.get(i).getPercentage());
                    productStdIds.add(productStdId);
                }
                conProDto.setProductPreId(productPreIds);
                conProDto.setProductConId(productConId);
                conProDto.setProductName(productName);
                conProDto.setPercentage(productPercentage);
                conProDto.setProductStdId(productStdIds);
                conProDto.setProductConDesc(productLibraryConfiguration.getProductConDesc());
                conProDto.setProductConNum(productLibraryConfiguration.getProductConNum());
                conProDto.setProductConPrice(productLibraryConfiguration.getProductConPrice());
                conProDto.setProductConName(productLibraryConfiguration.getProductConName());
                return new ResultBean<>(conProDto);
            }
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 添加配置
     *
     * @param configName
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Integer>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/28 10:31
     */
    @OperationLog(value = "添加配置")
    @PostMapping("/config/{configName}")
    public ResultBean<Integer> addConfig(@PathVariable String configName) {
        try {
            ProductLibraryConfiguration productLibraryConfiguration = new ProductLibraryConfiguration();
            productLibraryConfiguration.setProductConName(configName);
            Integer id = productLibraryConfigurationService.addConfig(configName);
            if (id == null) {
                throw new Exception("添加失败");
            } else {
                return new ResultBean<>(id);
            }
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 获取所有的配置
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryConfiguration>>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/29 13:54
     */
    @GetMapping(value = "/productLibraryConfigurations")
    public ResultBean<List<ProductLibraryConfiguration>> getProductLibraryConfigurations() {
        try {
            List<ProductLibraryConfiguration> productLibraryConfigurationList = productLibraryConfigurationService.getAllConfiguration();
            return new ResultBean<>(productLibraryConfigurationList);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }
}
