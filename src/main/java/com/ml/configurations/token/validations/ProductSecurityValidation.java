package com.ml.configurations.token.validations;

import com.ml.configurations.UserAuthenticationExtractor;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class ProductSecurityValidation implements SecurityValidation {

    private final UserAuthenticationExtractor userAuthenticationExtractor;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public AccessValidatorType getType() {
        return AccessValidatorType.PRODUCT;
    }

    @Override
    @Transactional
    public boolean hasEntityAccess(Authentication authentication, Long id) {
        User currentUser = this.userAuthenticationExtractor.getCurrentUser((String) authentication.getPrincipal());
        Product product = this.manager.find(Product.class, id);

        Assert.notNull(currentUser, "The user must be not null");
        Assert.notNull(product, "The product must be not null");
        Assert.notNull(product.getOwner(), "The product owner must be not null");

        return product.getOwner().getId().equals(currentUser.getId());
    }

}
