package com.ml.products.controllers.requests;

import com.ml.categories.entities.Category;
import com.ml.commons.validations.ExistsValue;
import com.ml.commons.validations.UniqueValue;
import com.ml.products.entities.Product;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class ProductRequest {

    @NotBlank
    @UniqueValue(columnName = "name", domainClass = Product.class)
    private final String name;

    @NotNull
    @Positive
    private final BigDecimal price;

    @NotNull
    @Positive
    private final Long quantityAvailable;

    @NotEmpty
    @Size(min = 3)
    @UniqueElements
    @Valid
    @Getter
    private final List<ProductCharacteristicRequest> characteristics;

    @NotBlank
    @Length(max = 1000)
    private final String description;

    @NotNull
    @Positive
    @ExistsValue(fieldName = "id", domainClass = Category.class)
    private final Long categoryId;

    public ProductRequest(@NotBlank final String name,
                          @NotNull @Positive final BigDecimal price,
                          @NotNull @Positive final Long quantityAvailable,
                          @NotEmpty @Size(min = 3) final List<ProductCharacteristicRequest> characteristics,
                          @NotBlank @Length(max = 1000) final String description,
                          @NotNull @Positive @ExistsValue(fieldName = "id", domainClass = Category.class) final Long categoryId) {
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.characteristics = characteristics;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Product toModel(EntityManager manager) {
        @NotNull Category category = manager.find(Category.class, this.categoryId);

        return new Product(this.name,
                this.price,
                this.quantityAvailable,
                this.characteristics,
                this.description,
                category);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantityAvailable=" + quantityAvailable +
                ", characteristics=" + characteristics +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
