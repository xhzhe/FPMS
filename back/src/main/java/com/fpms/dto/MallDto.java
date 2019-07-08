package com.fpms.dto;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/7/3 22:08
 * @description:
 * @modified :
 */
public class MallDto {

    private List<ConWithProNameDto> conWithProNameDtoList;

    private List<ProductDto> productDtoList;

    public List<ConWithProNameDto> getConWithProNameDtoList() {
        return conWithProNameDtoList;
    }

    public void setConWithProNameDtoList(List<ConWithProNameDto> conWithProNameDtoList) {
        this.conWithProNameDtoList = conWithProNameDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
