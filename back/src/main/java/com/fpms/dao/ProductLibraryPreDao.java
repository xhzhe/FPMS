package com.fpms.dao;

import com.fpms.entity.ProductLibraryPre;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductLibraryPreDao {
    int deleteByPrimaryKey(Integer productPreId);

    int insert(ProductLibraryPre record);

    int insertSelective(ProductLibraryPre record);

    ProductLibraryPre selectByPrimaryKey(Integer productPreId);

    int updateByPrimaryKeySelective(ProductLibraryPre record);

    int updateByPrimaryKey(ProductLibraryPre record);

    List<ProductLibraryPre> getAll();

    List<ProductLibraryPre> selectByProductStatus(Byte productStatus);

    /**
     * 获取预选库的所有产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 11:43
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryPre>
     */
    List<ProductLibraryPre> getAllProductPres();
}