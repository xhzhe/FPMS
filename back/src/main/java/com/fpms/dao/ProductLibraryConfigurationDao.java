package com.fpms.dao;

import com.fpms.dto.ProductLibraryConfigurationDto;
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

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       productConId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer productConId);

    /**
     * 插入ProductLibraryConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       record
     * @return     : int
     */
    int insert(ProductLibraryConfiguration record);

    /**
     * 插入ProductLibraryConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       record
     * @return     : int
     */
    int insertSelective(ProductLibraryConfiguration record);

    /**
     * 通过配置id查找配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       productConId
     * @return     : com.fpms.entity.ProductLibraryConfiguration
     */
    ProductLibraryConfiguration selectByPrimaryKey(Integer productConId);

    /**
     * 更新ProductLibraryConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(ProductLibraryConfiguration record);

    /**
     * 更新ProductLibraryConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(ProductLibraryConfiguration record);

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:56
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    List<ProductLibraryConfiguration> selectAll();

    /**
     *  通过评审状态获取记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 14:05
     * @param       reviewStatus
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    List<ProductLibraryConfigurationDto> selectByReviewStatus(Byte reviewStatus);
}