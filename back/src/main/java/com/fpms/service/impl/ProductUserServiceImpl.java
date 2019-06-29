package com.fpms.service.impl;

import com.fpms.dao.ProductUserDao;
import com.fpms.entity.ProductUser;
import com.fpms.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/28 9:30
 * @description:
 * @modified :
 */
@Service
public class ProductUserServiceImpl implements ProductUserService {
    @Autowired
    private ProductUserDao productUserDao;

    @Override
    public void addProductUser(ProductUser productUser) {
        productUserDao.insertSelective(productUser);
    }

    @Override
    public List<ProductUser> selectByUserId(Integer userId) {
        return productUserDao.selectByUserId(userId);
    }
}
