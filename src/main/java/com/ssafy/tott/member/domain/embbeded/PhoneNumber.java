package com.ssafy.tott.member.domain.embbeded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    private static final String PHONE_NUMBER_FORMAT = "^\\d{7,8}$";
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_FORMAT);

    @Column(name = "phone_number", unique = true, nullable = false)
    private String value;

    private PhoneNumber(String phoneNumber) {
        value = phoneNumber;
    }

    public static PhoneNumber from(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        return new PhoneNumber(phoneNumber);
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new RuntimeException();
        }
    }
}
