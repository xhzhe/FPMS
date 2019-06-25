package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:13
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryStandardServiceImpl implements ProductLibraryStandardService {
    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;

    @Override
    public Boolean obetainProducts(Integer ID){
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(ID);
        if(productLibraryStandard==null){
            return false;
        }
        productLibraryStandard.setIsSale(Byte.parseByte("0"));
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count==0){
            return false;
        }
        return true;
    }

    @Override
    public ProductLibraryStandard selectById(Integer productStdId) {
        return productLibraryStandardDao.selectByPrimaryKey(productStdId);
    }

    @Override
    public synchronized void updateProductStandard(ProductLibraryStandard productLibraryStandard) {
        productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
    }
}
