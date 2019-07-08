package com.fpms.dto;

import com.fpms.entity.ProductLibraryPre;

/**
 * @author : xuhuizhe
 * @date : 2019/7/8 11:44
 * @description:
 * @modified :
 */
public class productPreWithIsSale extends ProductLibraryPre {
    public Byte getIsSale() {
        return isSale;
    }

    public void setIsSale(Byte isSale) {
        this.isSale = isSale;
    }

    Byte isSale = new Byte("0");

}
