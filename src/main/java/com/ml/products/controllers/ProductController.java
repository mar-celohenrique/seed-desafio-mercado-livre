package com.ml.products.controllers;

import com.ml.commons.exceptions.EntityNotFoundException;
import com.ml.commons.storage.StorageClient;
import com.ml.configurations.security.AuthenticatedUser;
import com.ml.products.controllers.requests.AddPictureRequest;
import com.ml.products.controllers.requests.ProductRequest;
import com.ml.products.entities.Picture;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @PersistenceContext
    private EntityManager manager;

    private final StorageClient storageClient;

    @PostMapping("/products")
    @Transactional
    public Product create(@RequestBody @Valid ProductRequest request,
                          @AuthenticatedUser User user) {
        Product product = request.toModel(this.manager, user);
        this.manager.persist(product);
        return product;
    }

    @PreAuthorize("hasPermissionToManageProduct(#id)")
    @PostMapping("/products/{id}/picture")
    @Transactional
    public Product addPicture(@PathVariable("id") Long id, @RequestBody @Valid AddPictureRequest request) {
        Product product = Optional.ofNullable(this.manager.find(Product.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Product [" + id + "] not found!"));
        Set<Picture> pictures = this.storageClient.store(request.toModel(product));
        product.addPictures(pictures);
        this.manager.merge(product);
        return product;
    }

}
