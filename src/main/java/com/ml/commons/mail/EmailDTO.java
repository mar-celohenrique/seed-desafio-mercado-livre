package com.ml.commons.mail;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class EmailDTO {

    private String from;
    private String to;
    private String body;
    private String subject;
    private String name;

    public EmailDTO(String from, String to, String body, String subject, String name) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
        this.name = name;
    }

}
