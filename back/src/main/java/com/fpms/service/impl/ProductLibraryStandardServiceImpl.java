package com.fpms.service.impl;

import com.fpms.dao.ProductLibraryPreDao;
import com.fpms.dao.ProductLibraryStandardDao;
import com.fpms.dto.ProductWithName;

import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;

import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        this.productLibraryStandardDao=productLibraryStandardDao;
    }
    /**
     *  下架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:05
     * @param       id
     * @return     : java.lang.Boolean
     */
    @Override
    public Boolean obtainedProducts(Integer id) throws Exception {
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(id);
        if(productLibraryStandard==null){
            throw new Exception("产品不存在");
        }
        productLibraryStandard.setIsSale(Byte.parseByte("0"));
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count<=0){
            throw new Exception("下架失败");
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
    public ProductLibraryStandard selectById(Integer productStdId) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByPrimaryKey(productStdId);
        if(productLibraryStandard==null){
            throw new Exception("没有该产品");
        }
        return productLibraryStandard;
    }
    /**
     *  通过标准库
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:35
     * @param       productLibraryStandard
     * @return     : boolean
     */
    @Override
    public synchronized boolean updateProductStandard(ProductLibraryStandard productLibraryStandard) throws Exception {
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count>0){
            return true;
        }
        throw new Exception("更新失败");
    }

    /**
     * 上架产品
     *
     * @param id
     * @return : java.lang.Boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 10:35
     */
    @Override
    public Boolean uploadProduct(Integer id) throws Exception {
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(id);
        if(productLibraryStandard==null){
            throw new Exception("产品不存在");
        }
        productLibraryStandard.setIsSale(Byte.parseByte("1"));
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count>0){
            return true;
        }
        throw new Exception("上架失败");
    }

    /**
     * 通过产品预选id选择标准库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/28 16:45
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    @Override
    public ProductLibraryStandard selectByProductPreId(Integer productPreId) throws Exception {
        ProductLibraryStandard productLibraryStandard = productLibraryStandardDao.selectByProductPreId(productPreId);
        if(productLibraryStandard==null){
            throw new Exception("标准库中无该产品");
        }
        return productLibraryStandard;
    }

    /**
     *  获取所有标准库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 16:50
     * @param
     * @return     : ArrayList<ProductWithName>
     */
    @Override
    public ArrayList<ProductWithName> getAll() throws Exception {
        ArrayList<ProductWithName> productWithNames= new ArrayList<>();
        List<ProductLibraryStandard> productLibraryStandards = productLibraryStandardDao.selectAll();
        if(productLibraryStandards==null){
           throw new Exception("标准库中无产品");
        }
        for(ProductLibraryStandard productLibraryStandard: productLibraryStandards){
            ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
            if(productLibraryPre==null){
                throw new Exception("标准库中产品没有出现在预选库中，不合法产品");
            }
            String name=productLibraryPre.getProductName();
            ProductWithName productWithName=new ProductWithName();
            productWithName.setCreateTime(productLibraryStandard.getCreateTime());
            productWithName.setIsSale(productLibraryStandard.getIsSale());
            productWithName.setCreditRiskIndex(productLibraryStandard.getCreditRiskIndex());
            productWithName.setEvalutionAvgScore(productLibraryStandard.getEvalutionAvgScore());
            productWithName.setEvalutionNum(productLibraryStandard.getEvalutionNum());
            productWithName.setExchangeRateRiskIndex(productLibraryStandard.getExchangeRateRiskIndex());
            productWithName.setInterestRateRiskIndex(productLibraryStandard.getInterestRateRiskIndex());
            productWithName.setInterRiskRating(productLibraryStandard.getInterRiskRating());
            productWithName.setMarketRiskIndex(productLibraryStandard.getMarketRiskIndex());
            productWithName.setProductName(name);
            productWithName.setProductPreId(productLibraryStandard.getProductPreId());
            productWithName.setProductStdId(productLibraryStandard.getProductStdId());
            productWithName.setSaleEndTime(productLibraryStandard.getSaleEndTime());
            productWithName.setSaleNum(productLibraryStandard.getSaleNum());
            productWithName.setSaleStartTime(productLibraryStandard.getSaleStartTime());
            productWithName.setStock(productLibraryStandard.getStock());
            productWithName.setSuitUser(productLibraryStandard.getSuitUser());


            productWithNames.add(productWithName);
        }
        return productWithNames;
    }
}
