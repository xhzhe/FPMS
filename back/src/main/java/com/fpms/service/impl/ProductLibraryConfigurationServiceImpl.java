package com.fpms.service.impl;

import com.fpms.dao.ProductConfigurationDao;
import com.fpms.dao.ProductLibraryConfigurationDao;
import com.fpms.dto.ProductLibraryConfigurationDto;
import com.fpms.entity.ProductConfiguration;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.service.ProductLibraryConfigurationService;
import com.fpms.service.ProductLibraryPreService;
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

    private ProductLibraryConfigurationDao productLibraryConfigurationDao;

    private ProductConfigurationDao productConfigurationDao;

    @Autowired
    public void setProductLibraryPreService(ProductLibraryPreService productLibraryPreService) {
        this.productLibraryPreService = productLibraryPreService;
    }

    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    public ProductLibraryConfigurationServiceImpl(ProductConfigurationDao productConfigurationDao, ProductLibraryConfigurationDao productLibraryConfigurationDao) {
        this.productLibraryConfigurationDao = productLibraryConfigurationDao;
        this.productConfigurationDao = productConfigurationDao;
    }

    /**
     * 获取所有配置的信息
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/20 14:48
     */
    @Override
    public List<ProductLibraryConfiguration> getAllConfiguration() {
        return productLibraryConfigurationDao.selectAll();
    }

    /**
     * 向配置中增加产品
     *
     * @param productConfiguration
     * @return : void
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 16:39
     */
    @Override
    public void addConfigurationProduction(ProductConfiguration productConfiguration) throws Exception {
        int count = productConfigurationDao.insertSelective(productConfiguration);
        if (count <= 0) {
            throw new Exception("插入产品失败");
        }
    }

    /**
     * 通过配置id删除配置
     *
     * @param productConId
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/24 21:06
     */
    @Override
    public void deleteConfiguration(Integer productConId) throws Exception {
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(productConId);
        if (productLibraryConfiguration == null) {
            throw new Exception("要删除的配置不存在");
        }
        if (productLibraryConfiguration.getReviewStatus().equals(Byte.parseByte("1"))) {
            //处于已审核状态的删除，设置为已过期
            productLibraryConfiguration.setReviewStatus(Byte.parseByte("-2"));
            productLibraryConfigurationDao.updateByPrimaryKeySelective(productLibraryConfiguration);
            return;
        }
        productConfigurationDao.deleteByProductConId(productConId);
        int count = productLibraryConfigurationDao.deleteByPrimaryKey(productConId);
        if (count <= 0) {
            throw new Exception("删除配置主表失败");
        }
    }

    /**
     * 通过id查询配置库配置
     *
     * @param productConId
     * @return : com.fpms.entity.ProductLibraryConfiguration
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/7 20:40
     */
    @Override
    public ProductLibraryConfiguration selectById(Integer productConId) throws Exception {
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(productConId);
        if (productLibraryConfiguration == null) {
            throw new Exception("配置库中没有该配置");
        }
        if (productLibraryConfiguration.getProductConNum() < 0) {
            throw new Exception("非法配置，配置中产品数量为负");
        }
        return productLibraryConfiguration;
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
     *
     * @param productConId
     * @return : java.util.List<com.fpms.entity.ProductConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 16:45
     */
    @Override
    public List<ProductConfiguration> getProductConfigurationByproductConId(Integer productConId) {
        return productConfigurationDao.getProductConfigurationByproductConId(productConId);
    }

    /**
     * 修改配置信息
     *
     * @param productLibraryConfiguration
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:35
     */
    @Override
    public void modifyConfiguration(ProductLibraryConfiguration productLibraryConfiguration) throws Exception {
        Integer id = productLibraryConfiguration.getProductConId();
        ProductLibraryConfiguration productLibraryConfigurationTemp = productLibraryConfigurationDao.selectByPrimaryKey(id);
        if (productLibraryConfigurationTemp == null) {
            throw new Exception("配置id错误，配置库无对应配置");
        }

        int count = productLibraryConfigurationDao.updateByPrimaryKeySelective(productLibraryConfiguration);
        if (count > 0) {
            return;
        }
        throw new Exception("修改失败");
    }

    /**
     * 修改配置中产品比率
     *
     * @param productConId
     * @param productStdId
     * @param rate
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 17:54
     */
    @Override
    public void modifyConfigurationRate(Integer productConId, Integer productStdId, double rate) throws Exception {
        ProductLibraryPre productLibraryPre = productLibraryPreService.selectByStdId(productStdId);
        if (productLibraryPre.getPurchaseStartPoint().compareTo(BigDecimal.valueOf(rate)) >= 0) {
            throw new Exception("修改无效，产品没有达到起购价");
        }
        ProductConfiguration productConfiguration = productConfigurationDao.selectByPciAndPsi(productConId, productStdId);
        if (productConfiguration == null) {
            throw new Exception("配置关联项不存在");
        }
        BigDecimal percent = productConfiguration.getPercentage();
        productConfiguration.setPercentage(BigDecimal.valueOf(rate));
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(productConId);
        if (productLibraryConfiguration == null) {
            throw new Exception("该配置不存在");
        }
        productLibraryConfiguration.setReviewStatus(Byte.parseByte("0"));
        productLibraryConfiguration.setProductConPrice(productLibraryConfiguration.getProductConPrice().subtract(percent.subtract(BigDecimal.valueOf(rate))));
        modifyConfiguration(productLibraryConfiguration);
        int count = productConfigurationDao.updateByPrimaryKeySelective(productConfiguration);
        if (count > 0) {
            return;
        }
        throw new Exception("修改失败");
    }

    /**
     * 添加配置
     *
     * @param configName
     * @return : Integer
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/28 10:22
     */
    @Override
    public Integer addConfig(String configName) {
        ProductLibraryConfiguration productLibraryConfiguration = new ProductLibraryConfiguration();
        productLibraryConfiguration.setProductConName(configName);
        int count = productLibraryConfigurationDao.insertSelective(productLibraryConfiguration);
        if (count > 0) {
            return productLibraryConfiguration.getProductConId();
        }
        return null;
    }

    /**
     * 获取已上架的所有配置
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/7/3 23:42
     */
    @Override
    public List<ProductLibraryConfiguration> getConfigurationsOnSale() {
        return productLibraryConfigurationDao.getConfigurationsOnSale();
    }
}
