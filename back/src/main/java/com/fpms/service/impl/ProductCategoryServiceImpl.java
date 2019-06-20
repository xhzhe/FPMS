package com.fpms.service.impl;

import com.fpms.dao.ProductCategoryDao;
import com.fpms.entity.ProductCategory;
import com.fpms.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:11
 * @description:
 * @modified :
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryDao.insertSelective(productCategory);
    }

    @Override
    public void delProductCategory(Integer productCategoryId) {
        productCategoryDao.deleteByPrimaryKey(productCategoryId);
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        productCategoryDao.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public List<ProductCategory> selectAllProductCategory() {
        return productCategoryDao.selectAllProductCategory();
    }
}
