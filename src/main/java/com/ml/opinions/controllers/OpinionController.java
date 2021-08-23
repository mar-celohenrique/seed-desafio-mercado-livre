package com.ml.opinions.controllers;

import com.ml.configurations.security.AuthenticatedUser;
import com.ml.opinions.controllers.requests.OpinionRequest;
import com.ml.opinions.entities.Opinion;
import com.ml.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OpinionController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/opinions")
    @Transactional
    public Opinion create(@RequestBody @Valid OpinionRequest request, @AuthenticatedUser User user) {
        Opinion opinion = request.toModel(this.manager, user);
        this.manager.persist(opinion);
        return opinion;
    }

}
