package com.ml.questions.controllers.requests;

import com.ml.commons.validations.ExistsValue;
import com.ml.products.entities.Product;
import com.ml.questions.entities.Question;
import com.ml.users.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QuestionRequest {

    @NotBlank
    private String title;

    @NotNull
    @ExistsValue(fieldName = "id", domainClass = Product.class)
    private Long productId;

    public QuestionRequest(@NotBlank final String title,
                           @NotNull
                           @ExistsValue(fieldName = "id", domainClass = Product.class) final Long productId) {
        this.title = title;
        this.productId = productId;
    }

    public Question toModel(EntityManager manager, User user) {
        Product product = manager.find(Product.class, this.productId);

        Assert.notNull(product, "The product must exists to create a question.");
        Assert.notNull(user, "The user must exists to create a question.");

        return new Question(this.title, product, user);
    }

}
