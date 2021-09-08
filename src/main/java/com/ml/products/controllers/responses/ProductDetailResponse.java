package com.ml.products.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ml.products.entities.Picture;
import com.ml.products.entities.Product;
import com.ml.products.entities.ProductOpinions;
import com.ml.questions.entities.Question;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
public class ProductDetailResponse {

    private Set<String> pictures;

    private String name;

    private BigDecimal price;

    private Set<ProductCharacteristicDetail> characteristics;

    private String description;

    @JsonProperty("average_grade")
    private Double averageGrade;

    private Integer grades;

    private Set<ProductOpinionDetail> opinions;

    private Set<String> questions;

    @Deprecated
    public ProductDetailResponse() {
    }

    public ProductDetailResponse(Product product) {
        this.pictures = product.mapPictures(Picture::getLink);
        this.name = product.getName();
        this.price = product.getPrice();
        this.characteristics = product.mapCharacteristics(ProductCharacteristicDetail::new);
        this.description = product.getDescription();

        ProductOpinions productOpinions = new ProductOpinions(product.getOpinions());
        this.opinions = productOpinions.mapOpinions(ProductOpinionDetail::new);
        this.averageGrade = productOpinions.average();
        this.grades = productOpinions.total();

        this.questions = product.mapQuestions(Question::getTitle);
    }

}
