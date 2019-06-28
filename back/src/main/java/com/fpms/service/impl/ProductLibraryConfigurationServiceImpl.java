package com.fpms.service.impl;

import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.dto.ProductLibraryConfigurationDto;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.service.ProductLibraryConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:14
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryConfigurationServiceImpl implements ProductLibraryConfigurationService {

    @Autowired
    private ProductLibraryConfigurationDao productLibraryConfigurationDao;

    @Autowired
    private ProductConfigurationDao productConfigurationDao;

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/20 14:48
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    @Override
    public List<ProductLibraryConfiguration> getAllConfiguration() {
        return productLibraryConfigurationDao.selectAll();
    }

    /**
     * 向配置中增加产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 16:39
     * @param       productConfiguration
     * @return     : void
     */
    @Override
    public void addConfigurationProduction(ProductConfiguration productConfiguration) {
        productConfigurationDao.insert(productConfiguration);
    }

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 21:06
     * @param       productConId
     * @return     : void
     */
    @Override
    public void deleteConfiguration(Integer productConId) {
        productConfigurationDao.deleteByProductConId(productConId);
        productLibraryConfigurationDao.deleteByPrimaryKey(productConId);
    }

    @Override
    public ProductLibraryConfiguration selectById(Integer productConId) {
        return productLibraryConfigurationDao.selectByPrimaryKey(productConId);
    }

    @Override
    public synchronized void updateProductConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        productLibraryConfigurationDao.updateByPrimaryKeySelective(productLibraryConfiguration);
    }

    @Override
    public List<ProductLibraryConfigurationDto> getUnReviewProductList() {
        return productLibraryConfigurationDao.selectByReviewStatus(Byte.valueOf("0"));
    }

    /**
     * 通过配置id获取该配置中的所有产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 16:45
     * @param       productConId
     * @return     : java.util.List<com.fpms.entity.ProductConfiguration>
     */
    @Override
    public List<ProductConfiguration> getProductConfigurationByproductConId(Integer productConId) {
        return productConfigurationDao.getProductConfigurationByproductConId(productConId);
    }

    /**
     * 修改配置信息
     *
     * @param productLibraryConfiguration
     * @return : boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:35
     */
    @Override
    public boolean modifyConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        Integer id = productLibraryConfiguration.getProductConId();
        ProductLibraryConfiguration productLibraryConfigurationTemp = productLibraryConfigurationDao.selectByPrimaryKey(id);
        if(productLibraryConfigurationTemp==null){
            return false;
        }
        int count=productLibraryConfigurationDao.updateByPrimaryKeySelective(productLibraryConfiguration);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     * 修改配置中产品比率
     *
     * @param productConId
     * @param productStdId
     * @param rate
     * @return : boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:54
     */
    @Override
    public boolean modifyConfigurationRate(Integer productConId, Integer productStdId, double rate) {
        ProductConfiguration productConfiguration=productConfigurationDao.selectByPCIAndPSI(productConId,productStdId);
        if(productConfiguration==null){
            return false;
        }
        productConfiguration.setPercentage(BigDecimal.valueOf(rate));
        int count = productConfigurationDao.updateByPrimaryKeySelective(productConfiguration);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     *  添加配置
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/28 10:22
     * @param       configName
     * @return     : Integer
     */
    @Override
    public Integer addConfig(String configName) {
        ProductLibraryConfiguration productLibraryConfiguration=new ProductLibraryConfiguration();
        productLibraryConfiguration.setProductConName(configName);
        int count = productLibraryConfigurationDao.insertSelective(productLibraryConfiguration);
        if(count>0){
            return productLibraryConfiguration.getProductConId();
        }
        return null;
    }
}
