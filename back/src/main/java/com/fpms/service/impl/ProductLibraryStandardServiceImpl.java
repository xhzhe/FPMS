package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:13
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryStandardServiceImpl implements ProductLibraryStandardService {
    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;
    /**
     *  下架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:05
     * @param       ID
     * @return     : java.lang.Boolean
     */
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
    /**
     *  通过标准库iD获取标准库产品
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:34
     * @param       productStdId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    @Override
    public ProductLibraryStandard selectById(Integer productStdId) {
        return productLibraryStandardDao.selectByPrimaryKey(productStdId);
    }
    /**
     *  通过标准库
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:35
     * @param       productLibraryStandard
     * @return     : void
     */
    @Override
    public synchronized void updateProductStandard(ProductLibraryStandard productLibraryStandard) {
        productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
    }
}
