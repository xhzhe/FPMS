package com.fpms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fpms.dao.ProductCategoryDao;
import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.dao.SupplierDao;
import com.fpms.entity.ProductCategory;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.Supplier;
import com.fpms.service.ProductCategoryService;
import com.fpms.service.ProductLibraryPreService;

import com.fpms.service.StaffService;
import com.fpms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:12
 * @description:
 * @modified :
 */
@Service
public class ProductLibraryPreServiceImpl implements ProductLibraryPreService {
    private ProductLibraryPreDao productLibraryPreDao;
    private ProductLibraryStandardDao productLibraryStandardDao;
    private StaffService staffService;
    private SupplierService supplierService;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    @Autowired
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Autowired
    public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    @Autowired
    public void setProductLibraryStandardDao(ProductLibraryStandardDao productLibraryStandardDao) {
        this.productLibraryStandardDao = productLibraryStandardDao;
    }

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Autowired
    public ProductLibraryPreServiceImpl(ProductLibraryPreDao productLibraryPreDao) {
        this.productLibraryPreDao = productLibraryPreDao;
    }

    /**
     * 修改产品属性
     *
     * @param productLibraryPre
     * @throws Exception when nothing found there
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    @Override

    public void modifyProduct(ProductLibraryPre productLibraryPre) throws Exception {
        ProductLibraryPre productLibraryPrepare = productLibraryPreDao.selectByPrimaryKey(productLibraryPre.getProductPreId());
        if (productLibraryPrepare == null) {
            throw new Exception("预选库没有该产品");
        }
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByProductPreId(productLibraryPre.getProductPreId());
        if (productLibraryStandard != null) {
            if (productLibraryStandard.getIsSale().equals(Byte.parseByte("0"))) {
                productLibraryStandard.setIsSale(Byte.parseByte("-1"));
                int count = productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
                if (count <= 0) {
                    throw new Exception("修改标准库状体失败");
                }
            }
            if (productLibraryStandard.getIsSale().equals(Byte.parseByte("1"))) {
                throw new Exception("非法操作，不允许的修改");
            }
        }
        int count = productLibraryPreDao.updateByPrimaryKeySelective(productLibraryPre);
        if (count > 0) {
            return;
        }
        throw new Exception("更新失败");
    }

    /**
     * 添加产品
     *
     * @param productLibraryPre
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    @Override
    public void addProduct(ProductLibraryPre productLibraryPre) throws Exception {
        if (productLibraryPre.getProductName() == null) {
            throw new Exception("没有产品名");
        }
        if (productLibraryPre.getStaffId() == null) {
            throw new Exception("非法操作，没有员工执行的操作");
        }
        staffService.getSingleStaffDetail(productLibraryPre.getStaffId());
        if (productLibraryPre.getSupplierId() == null) {
            throw new Exception("不合理产品，该产品没有供应商");
        }
        supplierService.getSupplier(productLibraryPre.getSupplierId());
        int count = productLibraryPreDao.insertSelective(productLibraryPre);
        if (count > 0) {
            return;
        }
        throw new Exception("添加失败");
    }

    /**
     * 新的获取productpre
     *
     * @param productPreId
     * @return : java.lang.Object
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/9 11:46
     */
    @Override
    public Object selectByIdNew(Integer productPreId) throws Exception {
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByPrimaryKey(productPreId);
        if (productLibraryPre == null) {
            throw new Exception("预选库中没有该产品");
        }
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByProductPreId(productPreId);
        Byte isSale = productLibraryStandard == null ? null : productLibraryStandard.getIsSale();
        Integer categoryId = productLibraryPre.getCategoryId();
        ProductCategory productCategory = categoryId == null ? null : productCategoryDao.selectByPrimaryKey(categoryId);
        Integer supplierId = productLibraryPre.getSupplierId();
        Supplier supplier = supplierId == null ? null : supplierDao.selectByPrimaryKey(supplierId);
        String productPreWithIsSale = JSON.toJSONStringWithDateFormat(productLibraryPre, "yyyy-MM-dd HH:MM:SS", SerializerFeature.WriteDateUseDateFormat);
        StringBuffer product = new StringBuffer(productPreWithIsSale);
        product.insert(product.length() - 1, ",isSale:" + (isSale == null ? "null" : isSale.toString()));
        product.insert(product.length() - 1, ",supplierName:" + (supplier == null ? "null" : "\"" + supplier.getSupplierName() + "\""));
        product.insert(product.length() - 1, ",categoryName:" + (productCategory == null ? "null" : "\"" + productCategory.getCategoryName() + "\""));
        return JSON.parse(product.toString());
    }

    /**
     * 通过预选库Id获取预选库产品
     *
     * @param productPreId
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 12:04
     */
    @Override
    public ProductLibraryPre selectById(Integer productPreId) throws Exception {
        ProductLibraryPre productLibraryPre = productLibraryPreDao.selectByPrimaryKey(productPreId);
        if (productLibraryPre == null) {
            throw new Exception("不存在该预选库产品");
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
     * @return : String
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 11:41
     */
    @Override
    public List<Object> getAllProductPres() throws Exception {
        List<ProductLibraryPre> productLibraryPres = productLibraryPreDao.getAllProductPres();
        List<Object> products = new ArrayList<>();
        if (productLibraryPres == null) {
            throw new Exception("预选库为空");
        }
        for (ProductLibraryPre productLibraryPre : productLibraryPres) {
            Integer productPreId = productLibraryPre.getProductPreId();
            products.add(selectByIdNew(productPreId));
        }
        return products;
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
