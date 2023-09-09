package com.ssafy.tott.member.domain.embbeded;

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

    public static Password of(String password, PasswordEncoder passwordEncoder) {
        validatePasswordLength(password);
        validatePasswordPattern(password);
        return new Password(passwordEncoder.encode(password));
    }

    private static void validatePasswordLength(String password) {
        int length = password.length();
        if (length < MIN_LENGTH || MAX_LENGTH < length) {
            throw new RuntimeException();
        }
    }

    private static void validatePasswordPattern(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new RuntimeException();
        }
    }
}
