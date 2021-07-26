package com.ml.configurations;

import com.ml.users.entities.User;
import com.ml.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class UserAuthenticationExtractor {

    private final UserRepository userRepository;

    public User getCurrentUser(String principal) {
        if (hasText(principal)) {
            return this.userRepository.findByLogin(principal)
                    .orElseThrow(() -> new InternalAuthenticationServiceException("A valid authentication was not found for current request"));
        }

        throw new InternalAuthenticationServiceException("A valid authentication was not found for current request");
    }

}
