package com.ml.opinions.controllers.requests;

import com.ml.commons.validations.ExistsValue;
import com.ml.opinions.entities.Opinion;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OpinionRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer note;

    @NotBlank
    private String title;

    @NotBlank
    @Length(max = 500)
    private String description;

    @NotNull
    @ExistsValue(fieldName = "id", domainClass = Product.class)
    private Long productId;

    public OpinionRequest(@NotNull
                          @Min(1)
                          @Max(5) final Integer note,
                          @NotBlank final String title,
                          @NotBlank
                          @Length(max = 500) final String description,
                          @NotNull
                          @ExistsValue(fieldName = "id", domainClass = Product.class) final Long productId) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.productId = productId;
    }

    public Opinion toModel(EntityManager manager, User user) {
        @NotNull Product product = manager.find(Product.class, this.productId);

        Assert.notNull(product, "The product must exists to create an opinion.");
        Assert.notNull(user, "The user must exists to create an opinion.");

        return new Opinion(this.note, this.title, this.description, product, user);
    }
}
