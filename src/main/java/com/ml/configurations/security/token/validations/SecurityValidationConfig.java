package com.ml.configurations.security.token.validations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class SecurityValidationConfig {

    @Bean
    public Map<AccessValidatorType, SecurityValidation> accessValidators(Set<SecurityValidation> validators) {
        return validators.stream().collect(
                Collectors.toMap(securityValidation -> Optional.ofNullable(securityValidation.getType()).orElseThrow(),
                        validator -> validator));
    }

}
