package com.ml.products.controllers;

import com.ml.commons.exceptions.EntityNotFoundException;
import com.ml.products.controllers.responses.ProductDetailResponse;
import com.ml.products.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
public class ProductDetailsController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/products/{id}/details")
    public ProductDetailResponse getDetails(@PathVariable("id") Long id) {
        Product product = Optional.ofNullable(this.manager.find(Product.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Product [" + id + "] not found!"));
        return new ProductDetailResponse(product);
    }

}
