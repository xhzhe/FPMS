package com.fpms.dao;

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

    List<ProductLibraryStandard> selectAll();
}