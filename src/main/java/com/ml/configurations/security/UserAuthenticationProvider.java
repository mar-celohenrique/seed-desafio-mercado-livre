package com.ml.configurations.security;

import com.ml.users.entities.User;
import com.ml.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static java.util.Objects.nonNull;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String login = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            User user = this.repository.findByLogin(login).orElseThrow(() -> new BadCredentialsException("Bad credentials."));

            if (nonNull(user)) {
                if (this.passwordEncoder.matches(password, user.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(user.getLogin(),
                            user.getPassword(),
                            List.of(new SimpleGrantedAuthority("USER")));
                }
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            throw new BadCredentialsException("Bad credentials.");
        }

        throw new BadCredentialsException("Bad credentials.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
