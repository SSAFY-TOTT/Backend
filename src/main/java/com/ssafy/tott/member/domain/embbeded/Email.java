package com.ssafy.tott.member.domain.embbeded;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Email {
    @Column(name = "email", unique = true, nullable = false)
    private String value;

    private Email(String email) {
        value = email;
    }

    public static Email from(String email) {
        return new Email(email);
    }
}
