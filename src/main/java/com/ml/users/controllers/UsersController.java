package com.ml.users.controllers;

import com.ml.users.controllers.requests.UserRequest;
import com.ml.users.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsersController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<User> create(@RequestBody @Valid UserRequest request) {
        User user = request.toModel();
        this.entityManager.persist(user);
        return ResponseEntity.ok(user);
    }

}
