package com.ml.configurations.security;

import com.ml.users.entities.User;
import com.ml.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class UserAuthenticationExtractor {

    private final UserRepository userRepository;

    private final String errorMessage = "A valid authentication was not found for current request";

    public User getCurrentUserFromAuthentication(Authentication authentication) {
        if (nonNull(authentication) && hasText((CharSequence) authentication.getPrincipal())) {
            return this.userRepository.findByLogin((String) authentication.getPrincipal())
                    .orElseThrow(() -> new InternalAuthenticationServiceException(this.errorMessage));
        }

        throw new InternalAuthenticationServiceException(this.errorMessage);
    }

}
