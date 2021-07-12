package com.ml.users.controllers.requests;

import com.ml.users.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    @Email
    private final String login;

    @NotBlank
    @Min(6)
    private final String password;

    public UserRequest(@NotBlank @Email String login, @NotBlank @Min(6) String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel() {
        return new User(this.login, this.password);
    }
}
