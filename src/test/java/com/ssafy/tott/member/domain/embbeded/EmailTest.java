package com.ssafy.tott.member.domain.embbeded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class EmailTest {

    @DisplayName("올바른 형식의 이메일을 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void success() {
        /* Given */
        String validEmailString = "shinhan0915@gmail.com";

        /* When */
        Email email = Email.from(validEmailString);

        /* Then */
        assertAll(
                () -> assertThat(email).isNotNull(),
                () -> assertThat(email.getValue()).isEqualTo(validEmailString));
    }

    @DisplayName("올바른 형식의 이메일을 입력하지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(
            strings = {
                    "신한0915@gmail.com",
                    "shinhan0915gmail.com",
                    "shinhan0915@gmailcom",
                    "shinhanbank20230915@gmail.com",
                    "shinhan0915@gmail.sinhan"
            })
    void fail(String invalidEmailString) {
        /* Given */
        /* When */
        /* Then */
        assertThatThrownBy(() -> Email.from(invalidEmailString)).isInstanceOf(RuntimeException.class);
    }
}
