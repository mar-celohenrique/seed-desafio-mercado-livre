package com.ml.products.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
public class ProductCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @Deprecated
    public ProductCharacteristic() {
    }

    public ProductCharacteristic(@NotBlank final String name,
                                 @NotBlank final String description,
                                 @NotNull @Valid final Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductCharacteristic that = (ProductCharacteristic) o;

        return Objects.nonNull(id) ? Objects.equals(id, that.id) : Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return 1239812895;
    }

}
