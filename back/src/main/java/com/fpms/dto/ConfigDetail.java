package com.fpms.dto;
import com.fpms.entity.ProductLibraryConfiguration;

import java.util.ArrayList;

/**
 * @author : xuhuizhe
 * @date : 2019/6/19 11:35
 * @description:
 * @modified :
 */
public class ConfigDetail {
    public ProductLibraryConfiguration configlib;
    public ArrayList<Product> products=new ArrayList<>();
    public void addProduct(String name, String desc, Float rate,int productLibraryId){
        Product p=new Product();
        p.rate=rate;
        p.describe=desc;
        p.productStdId=productLibraryId;
        p.productName=name;
        products.add(p);
    }
}
