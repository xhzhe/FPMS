package com.fpms.dao;

import com.fpms.entity.ProductConfiguration;

public interface ProductConfigurationDao {
    int deleteByPrimaryKey(Integer configurationId);

    int insert(ProductConfiguration record);

    int insertSelective(ProductConfiguration record);

    ProductConfiguration selectByPrimaryKey(Integer configurationId);

    int updateByPrimaryKeySelective(ProductConfiguration record);

    int updateByPrimaryKey(ProductConfiguration record);
}