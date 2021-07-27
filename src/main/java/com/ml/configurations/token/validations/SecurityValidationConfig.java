package com.ml.configurations.token.validations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class SecurityValidationConfig {

    @Bean
    public Map<AccessValidatorType, SecurityValidation> accessValidators(Set<SecurityValidation> validators) {
        return validators.stream().collect(
                Collectors.toMap(SecurityValidation::getType, validator -> validator));
    }

}
