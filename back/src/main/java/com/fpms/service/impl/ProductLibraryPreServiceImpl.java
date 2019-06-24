package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.service.ProductLibraryPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:12
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryPreServiceImpl implements ProductLibraryPreService {
    @Autowired
    private ProductLibraryPreDao productLibraryPreDao;
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
}
