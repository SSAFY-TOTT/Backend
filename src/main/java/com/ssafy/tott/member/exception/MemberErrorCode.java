package com.ssafy.tott.member.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_INVALID_FIND_BY_ACCOUNT_NUMBER(400, "MEMBER_01", "잘못된 계좌 번호입니다."),
    ERROR_CLIENT_BY_NOT_EQUALS_BY_VALIDATION_MEMO(400, "MEMBER_02", "인증 번호가 잘못되었습니다.");
    private final int statusCode;
    private final String errorCode;
    private final String message;

    MemberErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
