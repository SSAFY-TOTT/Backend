package com.ssafy.tott.member.domain.embbeded;

import com.ssafy.tott.member.exception.MemberErrorCode;
import com.ssafy.tott.member.exception.MemberException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 16;
    private static final String PASSWORD_FORMAT =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);

    @Column(name = "password", nullable = false)
    private String value;

    private Password(String password) {
        value = password;
    }

    public static Password of(String password, String validPassword, PasswordEncoder passwordEncoder) {
        validateSamePassword(password, validPassword);
        validatePasswordLength(password);
        validatePasswordPattern(password);
        return new Password(passwordEncoder.encode(password));
    }

    private static void validateSamePassword(String password, String validPassword) {
        if (!password.equals(validPassword)) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_PASSWORD_IS_NOT_SAME_VALID_PASSWORD);
        }
    }

    private static void validatePasswordLength(String password) {
        int length = password.length();
        if (length < MIN_LENGTH || MAX_LENGTH < length) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_PASSWORD_IS_NOT_VALID);
        }
    }

    private static void validatePasswordPattern(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new MemberException(MemberErrorCode.ERROR_CLIENT_BY_PASSWORD_IS_NOT_VALID);
        }
    }
}
