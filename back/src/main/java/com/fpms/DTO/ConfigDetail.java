package com.fpms.DTO;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;

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
    public void addProduct(String name, String desc, Float rate,int productLibraryID){
        Product p=new Product();
        p.rate=rate;
        p.describe=name;
        p.productStdId=productLibraryID;
        p.productName=desc;
        products.add(p);
    }
}
