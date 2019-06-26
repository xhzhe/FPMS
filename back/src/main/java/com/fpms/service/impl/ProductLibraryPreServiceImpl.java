package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.service.ProductLibraryPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:12
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryPreServiceImpl implements ProductLibraryPreService {
    @Autowired
    private ProductLibraryPreDao productLibraryPreDao;
    /**
     *  修改产品属性
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:06
     * @param       productLibraryPre
     * @return     : java.lang.Boolean
     */
    @Override
    public Boolean modifyProduct(ProductLibraryPre productLibraryPre) {
        ProductLibraryPre productLibraryPrepare=productLibraryPreDao.selectByPrimaryKey(productLibraryPre.getProductPreId());
        try {
            Class productPre=Class.forName("com.fpms.entity.ProductLibraryPre");
            Method []methods=productPre.getMethods();
            for(Method method:methods){
                String methodName=method.getName();
                if("set".equals(methodName.substring(0,3))){
                    String getName="get"+methodName.substring(3);
                    Method m=productPre.getMethod(getName);
                    Object attribute=m.invoke(productLibraryPre);
                    if(attribute!=null){
                        method.invoke(productLibraryPrepare,attribute);
                    }
                }
            }
            int count=productLibraryPreDao.updateByPrimaryKeySelective(productLibraryPre);
            if(count>0){
                return true;
            }
        } catch (ClassNotFoundException|NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
    /**
     *  添加产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:06
     * @param       productLibraryPre
     * @return     : java.lang.Boolean
     */
    @Override
    public Boolean addProduct(ProductLibraryPre productLibraryPre) {
        try{
            int count=productLibraryPreDao.insertSelective(productLibraryPre);
            if(count>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public ProductLibraryPre selectById(Integer productPreId) {
        return productLibraryPreDao.selectByPrimaryKey(productPreId);
    }

    @Override
    public List<ProductLibraryPre> getUnReviewProductList() {
        return productLibraryPreDao.selectByProductStatus(Byte.valueOf("0"));
    }

    /**
     * 获取所有预选库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 11:41
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryPre>
     */
    @Override
    public List<ProductLibraryPre> getAllProductPres() {
        return productLibraryPreDao.getAllProductPres();
    }
}
