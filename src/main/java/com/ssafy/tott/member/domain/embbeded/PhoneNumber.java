package com.ssafy.tott.member.domain.embbeded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    @Column(name = "phone_number", unique = true, nullable = false)
    private String value;

    private PhoneNumber(String phoneNumber) {
        value = phoneNumber;
    }

    public static PhoneNumber from(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }
}
