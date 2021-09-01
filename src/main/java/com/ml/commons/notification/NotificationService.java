package com.ml.commons.notification;

import com.ml.commons.mail.EmailDTO;
import com.ml.commons.mail.MailSender;
import com.ml.questions.entities.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MailSender mailSender;

    public void notifyNewQuestion(Question question) {
        this.mailSender.send(EmailDTO.builder()
                .name("ML Notifications")
                .subject("New question")
                .body("New question from: ".concat(question.getUser().getLogin()))
                .from("notifications@ml.com")
                .to(question.getProduct().getOwner().getLogin())
                .build());
    }

}
