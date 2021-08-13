package com.ml.products.entities;

import com.ml.categories.entities.Category;
import com.ml.products.controllers.requests.ProductCharacteristicRequest;
import com.ml.users.entities.User;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
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
    private final Set<ProductCharacteristic> characteristics = new HashSet<>();

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private User owner;

    @Column(nullable = false, updatable = false)
    @NotNull
    @CreatedDate
    private Instant creationDate;

    @Deprecated
    public Product() {
    }

    public Product(@NotBlank final String name,
                   @NotNull @Positive final BigDecimal price,
                   @NotNull @Positive final Long quantityAvailable,
                   final @NotEmpty @Size(min = 3) @UniqueElements @Valid List<ProductCharacteristicRequest> characteristics,
                   @NotBlank @Length(max = 1000) final String description,
                   @NotNull @Valid final Category category,
                   @NotNull @Valid final User owner) {
        this.name = name;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.characteristics.addAll(characteristics.stream().map(c -> c.toModel(this)).collect(Collectors.toSet()));
        this.description = description;
        this.category = category;
        this.owner = owner;

        Assert.isTrue(this.characteristics.size() >= 3, "The product must have at least 3 characteristics");
    }

    public boolean belongsToUser(User user) {
        return this.owner.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;

        return Objects.nonNull(id) ? Objects.equals(id, product.id) : Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return 2042274511;
    }

}
