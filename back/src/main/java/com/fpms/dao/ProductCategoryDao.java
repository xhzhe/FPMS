package com.fpms.dao;

import com.fpms.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductCategoryDao {
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:57
     * @param       categoryId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     *  插入记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:57
     * @param       record
     * @return     : int
     */
    int insert(ProductCategory record);

    /**
     *  插入记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:57
     * @param       record
     * @return     : int
     */
    int insertSelective(ProductCategory record);

    /**
     *  通过主键查询一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:57
     * @param       categoryId
     * @return     : com.fpms.entity.ProductCategory
     */
    ProductCategory selectByPrimaryKey(Integer categoryId);

    /**
     *  通过主键更新记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:57
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(ProductCategory record);

    /**
     *  通过主键更新记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:58
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(ProductCategory record);

    /**
     *  返回所有记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 11:58
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductCategory>
     */
    List<ProductCategory> selectAllProductCategory();
}