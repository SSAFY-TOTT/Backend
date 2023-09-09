package com.ssafy.tott.account.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum AccountErrorCode implements ErrorCode {
    API_ERROR_INVALID_ACCOUNT_CODE(400, "ACCOUNT_01", "올바른 형식의 계좌번호가 아닙니다.");
    private int statusCode;
    private String errorCode;
    private String message;

    AccountErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
