package com.ml.configurations;

import com.ml.users.entities.User;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @PersistenceContext
    private EntityManager manager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String login = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            Query query = this.manager.createQuery("select user from User user where user.login = :login");
            query.setParameter("login", login);

            Optional<User> userOptional = Optional.ofNullable((User) query.getSingleResult());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
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
