package com.fpms.dao;

import com.fpms.dto.ProductDetail;
import com.fpms.entity.ProductLibraryStandard;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductLibraryStandardDao {
    int deleteByPrimaryKey(Integer productStdId);

    int insert(ProductLibraryStandard record);

    int insertSelective(ProductLibraryStandard record);

    ProductLibraryStandard selectByPrimaryKey(Integer productStdId);

    int updateByPrimaryKeySelective(ProductLibraryStandard record);

    int updateByPrimaryKey(ProductLibraryStandard record);

    /**
     * 通过产品预选id选择标准库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/28 16:47
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectByProductPreId(Integer productPreId);

    List<ProductLibraryStandard> selectAll();

    /**
     * 获取所有上架的商品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/7/3 23:09
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryStandard>
     */
    List<ProductLibraryStandard> getProductsOnSale();
}