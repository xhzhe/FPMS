package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.dao.ProductLibraryStandardDao;

import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;

import com.fpms.service.ProductLibraryStandardService;
import com.google.common.collect.Maps;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:13
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryStandardServiceImpl implements ProductLibraryStandardService {
    private ProductLibraryStandardDao productLibraryStandardDao;
    private ProductLibraryPreDao productLibraryPreDao;

    @Autowired
    public void setProductLibraryPreDao(ProductLibraryPreDao productLibraryPreDao) {
        this.productLibraryPreDao = productLibraryPreDao;
    }

    @Autowired
    public ProductLibraryStandardServiceImpl(ProductLibraryStandardDao productLibraryStandardDao) {
        this.productLibraryStandardDao = productLibraryStandardDao;
    }

    /**
     * 下架产品
     *
     * @return : void
     * @throws Exception when product not exist
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/13 21:30
     * @parameter : id
     */
    @Override
    public void obtainedProducts(Integer id) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByPrimaryKey(id);
        if (productLibraryStandard == null) {
            throw new Exception("产品不存在");
        }
        productLibraryStandard.setIsSale(Byte.parseByte("0"));
        int count = productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if (count <= 0) {
            throw new Exception("下架失败");
        }
    }

    /**
     * 过标准库iD获取标准库产品
     *
     * @return : com.fpms.entity.ProductLibraryStandard
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/13 21:31
     * @parameter : productStdId
     */
    @Override
    public ProductLibraryStandard selectById(Integer productStdId) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByPrimaryKey(productStdId);
        if (productLibraryStandard == null) {
            throw new Exception("没有该产品");
        }

        return productLibraryStandard;
    }

    /**
     * 通过标准库
     *
     * @param productLibraryStandard
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/25 10:35
     */
    @Override
    public synchronized void updateProductStandard(ProductLibraryStandard productLibraryStandard) throws Exception {
        int count = productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if (count > 0) {
            return;
        }
        throw new Exception("更新失败");
    }

    /**
     * 上架产品
     *
     * @param id
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 10:35
     */
    @Override
    public void uploadProduct(Integer id) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByPrimaryKey(id);
        if (productLibraryStandard == null) {
            throw new Exception("产品不存在");
        }
        productLibraryStandard.setIsSale(Byte.parseByte("1"));
        int count = productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if (count > 0) {
            return;
        }
        throw new Exception("上架失败");
    }

    /**
     * 通过产品预选id选择标准库产品
     *
     * @param productPreId
     * @return : com.fpms.entity.ProductLibraryStandard
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/28 16:45
     */
    @Override
    public ProductLibraryStandard selectByProductPreId(Integer productPreId) throws Exception {
        ProductLibraryStandard productLibraryStandard;
        try {
            productLibraryStandard = productLibraryStandardDao.selectByProductPreId(productPreId);
        } catch (MyBatisSystemException e) {
            throw new Exception("数据库异常，有多个标准库产品和一个预选库产品对应");
        }
        if (productLibraryStandard == null) {
            throw new Exception("标准库中无该产品");
        }
        return productLibraryStandard;
    }

    /**
     * 获取所有标准库产品
     *
     * @param
     * @return : ArrayList<BeanMap>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/28 16:50
     */
    @Override
    public ArrayList<HashMap<String, Object>> getAll() throws Exception {
        ArrayList<HashMap<String, Object>> productWithNames = new ArrayList<>();
        List<ProductLibraryStandard> productLibraryStandards = productLibraryStandardDao.selectAll();
        if (productLibraryStandards == null) {
            throw new Exception("标准库中无产品");
        }
        for (ProductLibraryStandard productLibraryStandard : productLibraryStandards) {
            productWithNames.add(makeProductWithName(productLibraryStandard));
        }
        return productWithNames;
    }

    /**
     * 查找单个标准库产品
     *
     * @param id
     * @return : BeanMap
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 16:05
     */
    @Override
    public HashMap<String, Object> getProductStd(Integer id) throws Exception {
        ProductLibraryStandard productLibraryStandard = selectById(id);
        return makeProductWithName(productLibraryStandard);
    }

    /**
     * 插入标准库产品
     *
     * @param productLibraryStandard
     * @return : void
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/3 16:51
     */
    @Override
    public void insertProductStd(ProductLibraryStandard productLibraryStandard) throws Exception {
        try {
            selectByProductPreId(productLibraryStandard.getProductPreId());
            throw new RuntimeException("标准库存在该预选库产品");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            if (!"标准库中无该产品".equals(e.getMessage())) {
                throw e;
            }
        }
        int count = productLibraryStandardDao.insertSelective(productLibraryStandard);
        if (count > 0) {
            return;
        }
        throw new Exception("插入失败");
    }

    private HashMap<String, Object> makeProductWithName(ProductLibraryStandard productLibraryStandard) throws Exception {
        if (productLibraryStandard.getProductPreId() == null) {
            throw new Exception("非法产品，不存在预选库id");
        }
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
        if (productLibraryPre == null) {
            throw new Exception("标准库中产品没有出现在预选库中，不合法产品");
        }
        String name = productLibraryPre.getProductName();
        HashMap<String, Object> product = Maps.newHashMap();
        BeanMap.create(productLibraryStandard).forEach((k, v) ->
                product.put(k.toString(), v)
        );
        product.put("productName", name);
        return product;
    }

    /**
     * 获取所有上架的产品
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryStandard>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/7/3 23:09
     */
    @Override
    public List<ProductLibraryStandard> getProductsOnSale() {
        return productLibraryStandardDao.getProductsOnSale();
    }
}
