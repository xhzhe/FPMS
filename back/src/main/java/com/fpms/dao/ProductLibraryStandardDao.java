package com.fpms.dao;

import com.fpms.entity.ProductLibraryStandard;

public interface ProductLibraryStandardDao {
    int deleteByPrimaryKey(Integer productStdId);

    int insert(ProductLibraryStandard record);

    int insertSelective(ProductLibraryStandard record);

    ProductLibraryStandard selectByPrimaryKey(Integer productStdId);

    int updateByPrimaryKeySelective(ProductLibraryStandard record);

    int updateByPrimaryKey(ProductLibraryStandard record);
}