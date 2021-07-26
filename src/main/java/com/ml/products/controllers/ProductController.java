package com.ml.products.controllers;

import com.ml.products.controllers.requests.ProductRequest;
import com.ml.products.entities.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProductController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/products")
    @Transactional
    public Product create(@RequestBody @Valid ProductRequest request) {
        Product product = request.toModel(this.manager);
        this.manager.persist(product);
        return product;
    }

}
