package com.fpms.dao;

import com.fpms.entity.ProductUser;

public interface ProductUserDao {
    int deleteByPrimaryKey(Integer productUserId);

    int insert(ProductUser record);

    int insertSelective(ProductUser record);

    ProductUser selectByPrimaryKey(Integer productUserId);

    int updateByPrimaryKeySelective(ProductUser record);

    int updateByPrimaryKey(ProductUser record);
}