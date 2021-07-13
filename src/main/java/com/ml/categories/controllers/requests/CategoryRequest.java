package com.ml.categories.controllers.requests;

import com.ml.categories.entities.Category;
import com.ml.commons.validations.ExistsValue;
import com.ml.commons.validations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import static java.util.Objects.nonNull;

public class CategoryRequest {

    @NotBlank
    @UniqueValue(columnName = "name", domainClass = Category.class)
    private final String name;

    @ExistsValue(fieldName = "id", domainClass = Category.class)
    private final Long categoryId;

    public CategoryRequest(@NotBlank String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public Category toModel(EntityManager manager) {
        Category category = new Category(this.name);

        if (nonNull(this.categoryId)) {
            category.setCategory(manager.find(Category.class, this.categoryId));
        }

        return category;
    }
}
