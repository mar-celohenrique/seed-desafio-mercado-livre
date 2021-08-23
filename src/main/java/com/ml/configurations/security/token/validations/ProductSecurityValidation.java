package com.ml.configurations.security.token.validations;

import com.ml.configurations.security.UserAuthenticationExtractor;
import com.ml.products.entities.Product;
import com.ml.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static java.util.Objects.isNull;

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
        User currentUser = this.userAuthenticationExtractor.getCurrentUserFromAuthentication(authentication);
        Product product = this.manager.find(Product.class, id);

        if (isNull(currentUser) || isNull(product) || isNull(product.getOwner())) {
            return true;
        }

        return product.belongsToUser(currentUser);
    }

}
