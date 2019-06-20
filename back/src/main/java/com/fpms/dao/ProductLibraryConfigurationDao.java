package com.fpms.dao;

import com.fpms.entity.ProductLibraryConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductLibraryConfigurationDao {
    int deleteByPrimaryKey(Integer productConId);

    int insert(ProductLibraryConfiguration record);

    int insertSelective(ProductLibraryConfiguration record);

    ProductLibraryConfiguration selectByPrimaryKey(Integer productConId);

    int updateByPrimaryKeySelective(ProductLibraryConfiguration record);

    int updateByPrimaryKey(ProductLibraryConfiguration record);

    List<ProductLibraryConfiguration> selectAll();
}