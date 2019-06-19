package com.fpms.DTO;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryStandard;

import java.util.ArrayList;

/**
 * @author : xuhuizhe
 * @date : 2019/6/19 11:35
 * @description:
 * @modified :
 */
public class ConfigDetail {
    public ProductLibraryConfiguration configlib;
    public ArrayList<ProductLibraryStandard> products=new ArrayList<>();
    public ArrayList<Float>rate=new ArrayList<>();

}
