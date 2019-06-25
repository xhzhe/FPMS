package com.fpms.service.impl;

import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.service.ProductLibraryConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
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
}
