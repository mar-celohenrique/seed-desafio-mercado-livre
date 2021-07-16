package com.ml.users.entities;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.PrePersist;

@Configurable(autowire = Autowire.BY_TYPE)
public class UserListener {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PrePersist
    private void beforePersist(Object object) {
        Assert.notNull(object, "Entity must not be null!");

        if (User.class.isAssignableFrom(object.getClass())) {
            User user = (User) object;
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        }
    }

}
