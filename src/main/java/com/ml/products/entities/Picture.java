package com.ml.products.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PictureType type;

    @Transient
    @Setter
    private String base64;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @Deprecated
    public Picture() {
    }

    public Picture(PictureType type, String base64, Product product) {
        this.type = type;
        this.base64 = base64;
        this.product = product;
        this.name = this.generateRandomName();
    }

    private String generateRandomName() {
        Assert.notNull(this.product, "The product must be not null");
        Assert.notNull(this.product.getName(), "The product name must be not null");

        int sizeName = this.product.getName().length();
        String productName = Normalizer.normalize(this.product.getName(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return productName
                .substring(0, Math.min(sizeName, 12))
                .toLowerCase(Locale.ROOT)
                .replaceAll(" ", "_").concat("_" + ThreadLocalRandom.current().nextInt());
    }

}
