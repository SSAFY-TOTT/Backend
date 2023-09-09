package com.ssafy.tott.member.domain.embbeded;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordTest {
  @DisplayName("올바른 형식의 비밀번호를 입력하면 객체가 정상적으로 생성된다.")
  @Test
  void success() {
    /* Given */
    String validPasswordString = "Password1234!@";
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /* When */
    Password password = Password.of(validPasswordString, encoder);

    /* Then */
    assertThat(password).isNotNull();
  }

  @DisplayName("올바른 형식의 비밀번호를 입력하지 않으면 예외를 던진다.")
  @ParameterizedTest
  @ValueSource(
      strings = {
        "password1234!@",
        "PASSWORD1234!@",
        "비밀번호1234!@",
        "Password1234",
        "Password!@",
        "Password1234_"
      })
  void fail(String invalidPassword) {
    /* Given */
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /* When */
    /* Then */
    assertThatThrownBy(() -> Password.of(invalidPassword, encoder))
        .isInstanceOf(RuntimeException.class);
  }
}
