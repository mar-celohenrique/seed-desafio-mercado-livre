package com.ml.configurations.expressions;

import com.ml.configurations.token.validations.AccessValidatorType;
import com.ml.configurations.token.validations.SecurityValidation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class UserExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final Map<AccessValidatorType, SecurityValidation> validators;

    private Object filterObject;

    private Object returnObject;

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     * @param validators the map of SecurityValidation and types
     */
    public UserExpressionRoot(Authentication authentication, Map<AccessValidatorType, SecurityValidation> validators) {
        super(authentication);
        this.validators = validators;
    }

    public boolean hasPermissionToManageProduct(Long productId) {
        SecurityValidation securityValidation = Optional.ofNullable(this.validators.get(AccessValidatorType.PRODUCT))
                .orElseThrow();
        return nonNull(productId) && securityValidation.hasEntityAccess(super.authentication, productId);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

}
