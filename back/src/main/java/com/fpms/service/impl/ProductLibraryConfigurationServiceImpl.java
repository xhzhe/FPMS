package com.fpms.service.impl;

import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.dto.ProductLibraryConfigurationDto;
import com.fpms.entity.ProductConfiguration;
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
    public List<ProductLibraryConfigurationDto> getAllConfiguration() {
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
}
