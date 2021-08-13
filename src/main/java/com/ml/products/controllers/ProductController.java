package com.ml.products.controllers;

import com.ml.configurations.security.AuthenticatedUser;
import com.ml.products.controllers.requests.ProductRequest;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Product create(@RequestBody @Valid ProductRequest request,
                          @AuthenticatedUser User user) {
        Product product = request.toModel(this.manager, user);
        this.manager.persist(product);
        return product;
    }

}
