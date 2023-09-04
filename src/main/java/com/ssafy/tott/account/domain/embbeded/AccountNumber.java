package com.ssafy.tott.account.domain.embbeded;

import com.ssafy.tott.account.exception.AccountErrorCode;
import com.ssafy.tott.account.exception.AccountException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AccountNumber {
    private static final String ACCOUNT_NUMBER_FORMAT = "^\\d{10,14}$";
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(ACCOUNT_NUMBER_FORMAT);

    @Column(name = "account_number")
    private String value;

    private AccountNumber(String value) {
        this.value = value;
    }

    public static AccountNumber from(String value) {
        validateAccountNumber(value);
        return new AccountNumber(value);
    }

    private static void validateAccountNumber(String value) {
        if (!ACCOUNT_NUMBER_PATTERN.matcher(value).matches()) {
            throw new AccountException(AccountErrorCode.API_ERROR_INVALID_ACCOUNT_CODE);
        }
    }
}
