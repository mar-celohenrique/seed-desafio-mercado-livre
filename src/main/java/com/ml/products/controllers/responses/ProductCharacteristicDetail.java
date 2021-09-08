package com.ml.products.controllers.responses;

import com.ml.products.entities.ProductCharacteristic;
import lombok.Getter;

@Getter
public class ProductCharacteristicDetail {

    private String name;

    private String description;

    @Deprecated
    public ProductCharacteristicDetail() {
    }

    public ProductCharacteristicDetail(ProductCharacteristic characteristic) {
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
    }

}
