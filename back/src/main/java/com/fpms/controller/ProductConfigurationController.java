package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.dto.AddConProDto;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    /**
     * 通过产品标准库id和产品配置删除配置中某一产品
     *
     * @param param
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/23 23:36
     */
    @OperationLog(value = "删除配置中的产品")
    @DeleteMapping(value = "/configuration/production")
    @Transactional(rollbackFor = Exception.class)
    public ResultBean<Boolean> deleteConfigurationProduction(@RequestBody Map<String, String> param) {
        try {
            if(!param.containsKey("productConfigId")){
                throw new Exception("没有配置id");
            }
            if(!param.containsKey("productStdId")){
                throw new Exception("没有标准库id");
            }
            if(param.get("productConfigId")==null){
                throw new Exception("配置id为null");
            }
            if(param.get("productStdId")==null){
                throw new Exception("标准库id为null");
            }
            Integer productConfigId = Integer.valueOf(param.get("productConfigId"));
            Integer productStdId = Integer.valueOf(param.get("productStdId"));
            ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(productConfigId);
            ProductConfiguration productConfiguration = productConfigurationDao.selectByPciAndPsi(productConfigId, productStdId);
            synchronized (ProductLibraryConfiguration.class) {
                if (productLibraryConfiguration.getProductConNum() == 0) {
                    throw new Exception("产品配置数量错误，该配置中无产品");
                }
                BigDecimal percent = productConfiguration.getPercentage();
                productLibraryConfiguration.setProductConNum(productLibraryConfiguration.getProductConNum() - 1);
                productLibraryConfiguration.setReviewStatus(Byte.parseByte("0"));
                productLibraryConfiguration.setProductConPrice(productLibraryConfiguration.getProductConPrice().subtract(percent));
                productLibraryConfigurationService.modifyConfiguration(productLibraryConfiguration);
            }
            productConfigurationDao.deleteByPrimaryKey(productConfiguration.getConfigurationId());
        } catch (MyBatisSystemException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>("数据库错误，可能为返回数据数量和预定不匹配,即配置中存在相同产品");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 向配置中增加产品
     *
     * @param addConProDto
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 16:38
     */
    @OperationLog(value = "增加配置中的产品")
    @PutMapping(value = "/configuration/production/actions/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultBean<Boolean> addConfigurationProduction(@RequestBody AddConProDto addConProDto) {
        try {
            if(addConProDto.getPercentage()==null){
                throw new Exception("没有产品的价格");
            }
            if(addConProDto.getProductConId()==null){
                throw new Exception("没有配置id");
            }
            if(addConProDto.getProductName()==null){
                throw new Exception("没有产品名字");
            }
            if (addConProDto.getPercentage().doubleValue() > 99999999) {
                throw new Exception("单个产品价格过高");
            }
            ProductConfiguration productConfiguration = new ProductConfiguration();
            ProductLibraryPre productLibraryPre = productLibraryPreService.selectByProductName(addConProDto.getProductName());

            Integer productPreId = productLibraryPre.getProductPreId();
            ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectByProductPreId(productPreId);
            ProductConfiguration productConfigurationTemp = productConfigurationDao.selectByPciAndPsi(addConProDto.getProductConId(), productLibraryStandard.getProductStdId());
            if(productConfigurationTemp!=null){
                throw new Exception("该配置中已存在该产品，不允许重复添加");
            }
            if (addConProDto.getPercentage().compareTo(productLibraryPre.getPurchaseStartPoint()) < 0) {
                throw new Exception("该产品没有达到产品的起购价，起购价为：" + productLibraryPre.getPurchaseStartPoint().toString());
            }
            //不对标准库库存进行操作，配置独立
//            if(addConProDto.getPercentage().intValue()>productLibraryStandard.getStock()){
////                throw new Exception("输入金额大于库存");
////            }else {
////                productLibraryStandard.setStock(productLibraryStandard.getStock()-addConProDto.getPercentage().intValue());
////                productLibraryStandardService.updateProductStandard(productLibraryStandard);
////            }
            ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(addConProDto.getProductConId());
            synchronized (ProductLibraryConfiguration.class) {
                productLibraryConfiguration.setProductConNum(productLibraryConfiguration.getProductConNum() + 1);
                productLibraryConfiguration.setReviewStatus(Byte.parseByte("0"));
                productLibraryConfiguration.setProductConPrice(productLibraryConfiguration.getProductConPrice().add(addConProDto.getPercentage()));
                productLibraryConfigurationService.modifyConfiguration(productLibraryConfiguration);

            }

            productConfiguration.setPercentage(addConProDto.getPercentage());
            productConfiguration.setProductConId(addConProDto.getProductConId());
            productConfiguration.setProductStdId(productLibraryStandard.getProductStdId());
            productLibraryConfigurationService.addConfigurationProduction(productConfiguration);
        } catch (MyBatisSystemException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>("数据库错误，可能为返回数据数量和预定不匹配,即配置中存在相同产品");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 修改配置信息
     *
     * @param productLibraryConfiguration
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:44
     */
    @OperationLog(value = "修改配置")
    @PutMapping("/configuration")
    public ResultBean<Boolean> modifyConfiguration(@RequestBody ProductLibraryConfiguration productLibraryConfiguration) {
        try {
            if (productLibraryConfiguration.getProductConNum() != null || productLibraryConfiguration.getProductConPrice() != null) {
                throw new Exception("不允许修改");
            }
            if (productLibraryConfiguration.getProductConDesc() != null || productLibraryConfiguration.getProductConName() != null) {
                productLibraryConfiguration.setReviewStatus(Byte.parseByte("0"));
            }
            productLibraryConfigurationService.modifyConfiguration(productLibraryConfiguration);
            return new ResultBean<>(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 修改配置比例
     *
     * @param configId
     * @param productStdId
     * @param rate
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 18:40
     */
    @OperationLog(value = "修改产品比例")
    @PutMapping("/configuration/{configId}/{productStdId}/{rate}")
    public ResultBean<Boolean> modifyConfigRate(@PathVariable Integer configId, @PathVariable Integer productStdId, @PathVariable double rate) {
        try {
            if (rate > 99999999) {
                throw new Exception("单个产品价格过高");
            }
            productLibraryConfigurationService.modifyConfigurationRate(configId, productStdId, rate);
            return new ResultBean<>(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }


}
