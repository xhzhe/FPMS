package com.fpms.dao;

import com.fpms.entity.ProductConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductConfigurationDao {
    int deleteByPrimaryKey(Integer configurationId);

    int insert(ProductConfiguration record);

    int insertSelective(ProductConfiguration record);

    ProductConfiguration selectByPrimaryKey(Integer configurationId);

    int updateByPrimaryKeySelective(ProductConfiguration record);

    int updateByPrimaryKey(ProductConfiguration record);

    List<ProductConfiguration> getProductConfigID(Integer proConfigID);
}