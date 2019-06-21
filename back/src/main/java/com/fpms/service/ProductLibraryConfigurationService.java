package com.fpms.service;

import com.fpms.dto.ProductLibraryConfigurationDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:52
 * @description:
 * @modified :
 */
public interface ProductLibraryConfigurationService {

    /**
     * 获取所有配置的信息
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/20 14:37
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryConfiguration>
     */
    List<ProductLibraryConfigurationDto> getAllConfiguration();

}
