package com.fpms.service;

import com.fpms.dto.ProductLibraryConfigurationDto;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:52
 * @description:
 * @modified :
 */
public interface ProductLibraryConfigurationService {

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/20 14:37
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    List<ProductLibraryConfiguration> getAllConfiguration();

    /**
     * 向配置中增加产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 16:40
     * @param       productConfiguration
     * @return     : void
     */
    void addConfigurationProduction(ProductConfiguration productConfiguration) ;

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 21:04
     * @param       productConId
     * @return     : void
     */
    void deleteConfiguration(Integer productConId);

    /**
     *  通过配置Id获取配置
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:38
     * @param       productConId
     * @return     : com.fpms.entity.ProductLibraryConfiguration
     */
    ProductLibraryConfiguration selectById(Integer productConId);

    /**
     *  通过配置Id更新配置
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:42
     * @param       productLibraryConfiguration
     * @return     : void
     */
    void updateProductConfiguration(ProductLibraryConfiguration productLibraryConfiguration);

    /**
     * 返回未评估的产品列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 14:02
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    List<ProductLibraryConfigurationDto> getUnReviewProductList();
}
