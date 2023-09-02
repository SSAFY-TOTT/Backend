package com.ssafy.tott.member.domain.embbeded;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PhoneNumberTest {
    @DisplayName("올바른 형식의 전화번호를 입력하면 객체가 정상적으로 생성된다.")
    @Test
    void success() {
        /* Given */
        String vaildPhoneNumberString = "12345678";

        /* When */
        PhoneNumber phoneNumber = PhoneNumber.from(vaildPhoneNumberString);

        /* Then */
        assertAll(
                () -> assertThat(phoneNumber).isNotNull(),
                () -> assertThat(phoneNumber.getValue()).isEqualTo(vaildPhoneNumberString)
        );
    }

    @DisplayName("올바른 형식의 전화번호를 입력하지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456789", "일이삼사오육칠팔", "1234567*", "abcdefggh"})
    void fail(String invalidPhoneNumber) {
        /* Given */
        /* When */
        /* Then */
        assertThatThrownBy(() -> PhoneNumber.from(invalidPhoneNumber))
                .isInstanceOf(RuntimeException.class);
    }
}