package com.ssafy.tott.member.domain.embbeded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password", nullable = false)
    private String value;

    private Password(String password) {
        value = password;
    }

    public static Password from(String password) {
        return new Password(password);
    }
}
