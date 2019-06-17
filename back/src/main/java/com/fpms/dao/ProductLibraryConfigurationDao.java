package com.fpms.dao;

import com.fpms.entity.ProductLibraryConfiguration;

public interface ProductLibraryConfigurationDao {
    int deleteByPrimaryKey(Integer productConId);

    int insert(ProductLibraryConfiguration record);

    int insertSelective(ProductLibraryConfiguration record);

    ProductLibraryConfiguration selectByPrimaryKey(Integer productConId);

    int updateByPrimaryKeySelective(ProductLibraryConfiguration record);

    int updateByPrimaryKey(ProductLibraryConfiguration record);
}