package com.ml.categories.controllers;

import com.ml.categories.controllers.requests.CategoryRequest;
import com.ml.categories.entities.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CategoryController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/categories")
    @Transactional
    public Category create(@RequestBody @Valid CategoryRequest request) {
        Category category = request.toModel(this.manager);
        this.manager.persist(category);
        return category;
    }

}
