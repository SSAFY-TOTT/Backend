package com.ssafy.tott.member.domain.embbeded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class EmailTest {

    @DisplayName("올바른 형식의 이메일을 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void success() {
        /* Given */
        String validEmailString = "sinhan0915@gmail.com";

        /* When */
        Email email = Email.from(validEmailString);

        /* Then */
        assertAll(
                () -> assertThat(email).isNotNull(),
                () -> assertThat(email.getValue()).isEqualTo(validEmailString)
        );
    }

    @DisplayName("이메일 로컬 영역이 올바르지 않으면 예외를 던진다.")
    @Test
    void fail01() {
        /* Given */
        String invalidEmailString = "신한0915@gmail.com";

        /* When */
        /* Then */
        assertThatThrownBy(() -> Email.from(invalidEmailString))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("이메일에 @가 없으면 예외를 던진다.")
    @Test
    void fail02() {
        /* Given */
        String inValidEmailString = "sinhan0915gmail.com";

        /* When */
        /* Then */
        assertThatThrownBy(() -> Email.from(inValidEmailString))
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("이메일 도메인 영역이 올바르지 않으면 예외를 던진다.")
    @Test
    void fail03() {
        /* Given */
        String inValidEmailString = "sinhan0915@gmailcom";

        /* When */
        /* Then */
        assertThatThrownBy(() -> Email.from(inValidEmailString))
                .isInstanceOf(RuntimeException.class);
    }
}