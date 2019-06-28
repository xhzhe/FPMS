package com.fpms.dto;

import com.fpms.entity.Evaluation;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;

/**
 * @author : TianHong Liao
 * @date : 2019/6/26 15:48
 * @description:
 * @modified :
 */
public class EvaluationDto {
    private Evaluation evaluation;
    private ProductLibraryPre productLibraryPre;
    private ProductLibraryConfiguration productLibraryConfiguration;
    private String userName;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public ProductLibraryPre getProductLibraryPre() {
        return productLibraryPre;
    }

    public void setProductLibraryPre(ProductLibraryPre productLibraryPre) {
        this.productLibraryPre = productLibraryPre;
    }

    public ProductLibraryConfiguration getProductLibraryConfiguration() {
        return productLibraryConfiguration;
    }

    public void setProductLibraryConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        this.productLibraryConfiguration = productLibraryConfiguration;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
