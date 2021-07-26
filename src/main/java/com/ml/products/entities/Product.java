package com.ml.products.entities;

import com.ml.categories.entities.Category;
import com.ml.products.controllers.requests.ProductCharacteristicRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EqualsAndHashCode(of = {"name"})
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity_available", nullable = false)
    private Long quantityAvailable;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<ProductCharacteristic> characteristics = new HashSet<>();

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    @Deprecated
    public Product() {
    }

    public Product(@NotBlank final String name,
                   @NotNull @Positive final BigDecimal price,
                   @NotNull @Positive final Long quantityAvailable,
                   final @NotEmpty @Size(min = 3) @UniqueElements @Valid List<ProductCharacteristicRequest> characteristics,
                   @NotBlank @Length(max = 1000) final String description,
                   @NotNull final Category category) {
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.characteristics.addAll(characteristics.stream().map(c -> c.toModel(this)).collect(Collectors.toSet()));
        this.description = description;
        this.category = category;

        Assert.isTrue(this.characteristics.size() >= 3, "The product must have at least 3 characteristics");
    }

}
