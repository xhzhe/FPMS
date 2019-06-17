package com.fpms.dao;

import com.fpms.entity.ProductLibraryPre;

public interface ProductLibraryPreDao {
    int deleteByPrimaryKey(Integer productPreId);

    int insert(ProductLibraryPre record);

    int insertSelective(ProductLibraryPre record);

    ProductLibraryPre selectByPrimaryKey(Integer productPreId);

    int updateByPrimaryKeySelective(ProductLibraryPre record);

    int updateByPrimaryKey(ProductLibraryPre record);
}