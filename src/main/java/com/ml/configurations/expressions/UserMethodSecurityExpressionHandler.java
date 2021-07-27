package com.ml.configurations.expressions;

import com.ml.configurations.token.validations.AccessValidatorType;
import com.ml.configurations.token.validations.SecurityValidation;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class UserMethodSecurityExpressionHandler extends OAuth2MethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    private final Map<AccessValidatorType, SecurityValidation> accessValidators;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
                                                                              MethodInvocation invocation) {
        UserExpressionRoot root = new UserExpressionRoot(authentication, this.accessValidators);
        root.setPermissionEvaluator(super.getPermissionEvaluator());
        root.setTrustResolver(this.authenticationTrustResolver);
        root.setRoleHierarchy(super.getRoleHierarchy());
        return root;
    }

}
