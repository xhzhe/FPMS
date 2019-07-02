package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
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

    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;

    /**
     * 修改产品属性
     *
     * @param productLibraryPre
     * @return : java.lang.Boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    @Override
    public Boolean modifyProduct(ProductLibraryPre productLibraryPre) throws Exception {
        ProductLibraryPre productLibraryPrepare = selectById(productLibraryPre.getProductPreId());
        if (productLibraryPrepare == null) {
            throw new Exception("预选库没有该产品");
        }
        //没有必要的反射
//            Class productPre=Class.forName("com.fpms.entity.ProductLibraryPre");
//            Method []methods=productPre.getMethods();
//            for(Method method:methods){
//                String methodName=method.getName();
//                if("set".equals(methodName.substring(0,3))){
//                    String getName="get"+methodName.substring(3);
//                    Method m=productPre.getMethod(getName);
//                    Object attribute=m.invoke(productLibraryPre);
//                    if(attribute!=null){
//                        method.invoke(productLibraryPrepare,attribute);
//                    }
//                }
//            }
        int count = productLibraryPreDao.updateByPrimaryKeySelective(productLibraryPre);
        if (count > 0) {
            return true;
        }
        throw new Exception("更新失败");
    }

    /**
     * 添加产品
     *
     * @param productLibraryPre
     * @return : java.lang.Boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    @Override
    public Boolean addProduct(ProductLibraryPre productLibraryPre) throws Exception {

        int count = productLibraryPreDao.insertSelective(productLibraryPre);
        if (count > 0) {
            return true;
        }
        throw new Exception("添加失败");
    }

    /**
     * 查询预选库产品
     *
     * @param productPreId
     * @return : com.fpms.entity.ProductLibraryPre
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/1 16:47
     */
    @Override
    public ProductLibraryPre selectById(Integer productPreId) throws Exception {
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByPrimaryKey(productPreId);
        if (productLibraryPre == null) {
            throw new Exception("预选库中没有该产品");
        }
        return productLibraryPre;
    }

    /**
     * 获取未评估产品列表
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryPre>
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/1 16:48
     */
    @Override
    public List<ProductLibraryPre> getUnReviewProductList() throws Exception {
        List<ProductLibraryPre> productLibraryPres = productLibraryPreDao.selectByProductStatus(Byte.valueOf("0"));
        if (productLibraryPres == null) {
            throw new Exception("未评估产品列表为空");
        }
        return productLibraryPres;
    }

    /**
     * 获取所有预选库产品
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryPre>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 11:41
     */
    @Override
    public List<ProductLibraryPre> getAllProductPres() throws Exception {
        List<ProductLibraryPre> productLibraryPres = productLibraryPreDao.getAllProductPres();
        if (productLibraryPres == null) {
            throw new Exception("预选库为空");
        }
        return productLibraryPres;
    }

    /**
     * 过标准库Id获得预选库的产品信息
     *
     * @param productStdId
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/26 15:58
     */
    @Override
    public ProductLibraryPre selectByStdId(Integer productStdId) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByPrimaryKey(productStdId);
        if (productLibraryStandard == null) {
            throw new Exception("标准库中没有该产品");
        }
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
        if (productLibraryPre == null) {
            throw new Exception("预选库中没有该产品，该产品可能不合法");
        }
        return productLibraryPre;
    }

    /**
     * 通过产品名查找产品
     *
     * @param productName
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/28 16:36
     */
    @Override
    public ProductLibraryPre selectByProductName(String productName) throws Exception {
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByProductName(productName);
        if (productLibraryPre == null) {
            throw new Exception("预选库中没有该产品");
        }
        return productLibraryPre;
    }
}
