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
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/20 14:37
     */
    List<ProductLibraryConfiguration> getAllConfiguration();

    /**
     * 向配置中增加产品
     *
     * @param productConfiguration
     * @return : void
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 16:40
     */
    void addConfigurationProduction(ProductConfiguration productConfiguration) throws Exception;

    /**
     * 通过配置id删除配置
     *
     * @param : productConId
     * @throws Exception
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 21:04
     */
    void deleteConfiguration(Integer productConId) throws Exception;

    /**
     * 通过配置Id获取配置
     *
     * @param : productConId
     * @return : com.fpms.entity.ProductLibraryConfiguration
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 10:38
     */
    ProductLibraryConfiguration selectById(Integer productConId) throws Exception;

    /**
     * 通过配置Id更新配置
     *
     * @param productLibraryConfiguration
     * @return : void
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 10:42
     */
    void updateProductConfiguration(ProductLibraryConfiguration productLibraryConfiguration);

    /**
     * 返回未评估的产品列表
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 14:02
     */
    List<ProductLibraryConfigurationDto> getUnReviewProductList();

    /**
     * 通过配置id获取该配置中的所有产品
     *
     * @param productConId
     * @return : java.util.List<com.fpms.entity.ProductConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 16:43
     */
    List<ProductConfiguration> getProductConfigurationByproductConId(Integer productConId);

    /**
     * 修改配置信息
     *
     * @param productLibraryConfiguration
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:35
     */
    void modifyConfiguration(ProductLibraryConfiguration productLibraryConfiguration) throws Exception;

    /**
     * 修改配置中产品比率
     *
     * @param configId
     * @param productStdId
     * @param rate
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:54
     */
    void modifyConfigurationRate(Integer configId, Integer productStdId, double rate) throws Exception;

    /**
     * 添加配置
     *
     * @param configName
     * @return : Integer
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/28 10:22
     */
    Integer addConfig(String configName);

    /**
     * 获取已上架的所有配置
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/7/3 23:41
     */
    List<ProductLibraryConfiguration> getConfigurationsOnSale();
}
