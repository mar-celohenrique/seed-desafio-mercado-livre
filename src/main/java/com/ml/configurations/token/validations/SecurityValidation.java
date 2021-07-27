package com.ml.configurations.token.validations;

import org.springframework.security.core.Authentication;

public interface SecurityValidation {

    boolean hasEntityAccess(Authentication authentication, Long id);

    AccessValidatorType getType();

}
