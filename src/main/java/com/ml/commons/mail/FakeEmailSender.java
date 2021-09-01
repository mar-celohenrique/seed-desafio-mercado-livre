package com.ml.commons.mail;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default", "dev"})
@Component
public class FakeEmailSender implements MailSender {

    @Override
    public void send(EmailDTO emailDTO) {
        System.out.println(emailDTO.toString());
    }

}
