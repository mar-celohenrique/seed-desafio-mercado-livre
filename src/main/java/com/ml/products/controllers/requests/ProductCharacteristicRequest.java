package com.ml.products.controllers.requests;

import com.ml.products.entities.Product;
import com.ml.products.entities.ProductCharacteristic;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@EqualsAndHashCode(of = {"name"})
public class ProductCharacteristicRequest {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    public ProductCharacteristicRequest(@NotBlank final String name,
                                        @NotBlank final String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCharacteristic toModel(@NotNull @Valid Product product) {
        return new ProductCharacteristic(this.name, this.description, product);
    }

}
