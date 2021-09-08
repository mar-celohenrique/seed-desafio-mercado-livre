package com.ml.products.controllers.responses;

import com.ml.opinions.entities.Opinion;
import lombok.Getter;

@Getter
public class ProductOpinionDetail {

    private String title;

    private String description;

    @Deprecated
    public ProductOpinionDetail() {
    }

    public ProductOpinionDetail(Opinion opinion) {
        this.title = opinion.getTitle();
        this.description = opinion.getDescription();
    }

}
