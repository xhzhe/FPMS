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
    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;

    @Autowired
    private ProductLibraryPreDao productLibraryPreDao;
    /**
     *  下架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:05
     * @param       id
     * @return     : java.lang.Boolean
     */
    @Override
    public Boolean obetainProducts(Integer id){
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(id);
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
     * @return     : boolean
     */
    @Override
    public synchronized boolean updateProductStandard(ProductLibraryStandard productLibraryStandard) {
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count>0){
            return true;
        }
        return false;
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
    public Boolean uploadProduct(Integer id) {
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(id);
        if(productLibraryStandard==null){
            return false;
        }
        productLibraryStandard.setIsSale(Byte.parseByte("1"));
        int count=productLibraryStandardDao.updateByPrimaryKeySelective(productLibraryStandard);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     * 通过产品预选id选择标准库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/28 16:45
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    @Override
    public ProductLibraryStandard selectByProductPreId(Integer productPreId) {
        return productLibraryStandardDao.selectByProductPreId(productPreId);
    }

    /**
     *  获取所有标准库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 16:50
     * @param
     * @return     : ArrayList<ProductWithName>
     */
    @Override
    public ArrayList<ProductWithName> getAll() {
        ArrayList<ProductWithName> productWithNames= new ArrayList<>();
        List<ProductLibraryStandard> productLibraryStandards = productLibraryStandardDao.selectAll();
        if(productLibraryStandards==null){
            return null;
        }
        for(ProductLibraryStandard productLibraryStandard: productLibraryStandards){
            ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
            if(productLibraryPre==null){
                return null;
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
