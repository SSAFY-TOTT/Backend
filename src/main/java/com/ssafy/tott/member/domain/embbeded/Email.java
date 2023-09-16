package com.ssafy.tott.member.domain.embbeded;

import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {
    private static final String EMAIL_FORMAT = "^[a-z0-9._-]{5,16}+@[a-z]+[.]+[a-z]{2,3}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_FORMAT);

    @Column(name = "email", unique = true, nullable = false)
    private String value;

    private Email(String email) {
        value = email;
    }

    public static Email from(String email) {
        validateEmailFormat(email);
        return new Email(email);
    }

    private static void validateEmailFormat(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_EMAIL_IS_NOT_VALID);
        }
    }
}
