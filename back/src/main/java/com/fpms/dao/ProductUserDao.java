package com.fpms.dao;

import com.fpms.entity.ProductUser;
import org.springframework.stereotype.Component;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductUserDao {
    int deleteByPrimaryKey(Integer productUserId);

    int insert(ProductUser record);

    int insertSelective(ProductUser record);

    ProductUser selectByPrimaryKey(Integer productUserId);

    int updateByPrimaryKeySelective(ProductUser record);

    int updateByPrimaryKey(ProductUser record);
}