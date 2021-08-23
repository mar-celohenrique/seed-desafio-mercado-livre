package com.ml.opinions.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer note;

    private String title;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @Deprecated
    public Opinion() {
    }

    public Opinion(@NotNull
                   @Min(1)
                   @Max(5) final Integer note,
                   @NotBlank final String title,
                   @NotBlank
                   @Length(max = 500) final String description,
                   @NotNull @Valid final Product product,
                   @NotNull @Valid final User user) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.product = product;
        this.user = user;
    }

}
