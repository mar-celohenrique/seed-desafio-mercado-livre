package com.ml.questions.controllers;

import com.ml.configurations.security.AuthenticatedUser;
import com.ml.questions.controllers.requests.QuestionRequest;
import com.ml.questions.entities.Question;
import com.ml.users.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class QuestionController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/questions")
    @Transactional
    public Question create(@RequestBody @Valid QuestionRequest request, @AuthenticatedUser User user) {
        Question question = request.toModel(this.manager, user);
        this.manager.persist(question);
        return question;
    }

}
