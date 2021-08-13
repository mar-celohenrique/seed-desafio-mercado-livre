package com.ml.configurations.security;

import org.springframework.security.core.annotation.CurrentSecurityContext;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@CurrentSecurityContext(expression = "@userAuthenticationExtractor.getCurrentUser(#this.getAuthentication())",
        errorOnInvalidType = true)
public @interface AuthenticatedUser {
}