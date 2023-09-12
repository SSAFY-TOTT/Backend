package com.ssafy.tott.account.domain.embbeded;

import com.ssafy.tott.account.exception.AccountErrorCode;
import com.ssafy.tott.account.exception.AccountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AccountNumberTest {
    @DisplayName("올바른 계좌번호를 입력하면 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234567890", "12345678901234"})
    void success(String input) {
        /* Given */
        /* When */
        AccountNumber accountNumber = AccountNumber.from(input);

        /* Then */
        assertAll(
                () -> assertThat(accountNumber).isNotNull(),
                () -> assertThat(accountNumber.getValue()).isEqualTo(input)
        );
    }

    @DisplayName("올바른 계좌번호를 입력하지 않으면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456789", "a1234567890", "123456789012345"})
    void fail(String input) {
        /* Given */
        /* When */
        /* Then */
        assertThatThrownBy(() -> AccountNumber.from(input))
                .isInstanceOf(AccountException.class)
                .hasMessageContaining(AccountErrorCode.API_ERROR_INVALID_ACCOUNT_CODE.getMessage());
    }
}