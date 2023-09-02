package com.ssafy.tott.member.domain.embbeded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {
    @DisplayName("올바른 형식의 비밀번호를 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void success() {
        /* Given */
        String validPasswordString = "Password1234!@";

        /* When */
        Password password = Password.from(validPasswordString);

        /* Then */
        assertThat(password).isNotNull();
    }

    @DisplayName("올바른 형식의 비밀번호를 입력하지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"password1234!@", "Password1234", "Password!@", "비밀번호1234!@", "Password1234_"})
    void fail(String invalidPassword) {
        /* Given */
        /* When */
        /* Then */
        assertThatThrownBy(() -> Password.from(invalidPassword))
                .isInstanceOf(RuntimeException.class);
    }
}