package com.fpms.service.impl;

import com.fpms.DTO.ConfigDetail;
import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:16
 * @description:
 * @modified :
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private ProductLibraryConfigurationDao productLibraryConfigurationDao;
    @Autowired
    private ProductConfigurationDao productConfigurationDao;
    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;

    @Override
    public ConfigDetail getConfigByID(Integer configID) {
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(configID);
        if(productLibraryConfiguration==null) {
            return null;
        }
        ConfigDetail res=new ConfigDetail();
        res.configlib=productLibraryConfiguration;
        List<ProductConfiguration> productConfigurations= productConfigurationDao.getProductConfigID(configID);

        for(ProductConfiguration productConfiguration:productConfigurations){
            ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(productConfiguration.getProductStdId());

            Float rate=Float.parseFloat(productConfiguration.getPercentage().toString());
            res.rate.add(rate);
            res.products.add(productLibraryStandard);
        }

        return res;
    }
}
