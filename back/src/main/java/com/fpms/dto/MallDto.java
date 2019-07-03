package com.fpms.dto;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/7/3 22:08
 * @description:
 * @modified :
 */
public class MallDto {

    private List<ConfigurationDto> configurationDtoList;

    private List<ProductDto> productDtoList;

    public List<ConfigurationDto> getConfigurationDtoList() {
        return configurationDtoList;
    }

    public void setConfigurationDtoList(List<ConfigurationDto> configurationDtoList) {
        this.configurationDtoList = configurationDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
