package com.ml.questions.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Column(nullable = false, updatable = false)
    @NotNull
    @CreatedDate
    private Instant creationDate;

    public Question() {
    }

    public Question(@NotBlank String title,
                    @NotNull @Valid Product product,
                    @NotNull @Valid User user) {
        this.title = title;
        this.product = product;
        this.user = user;
    }

}
