package com.ssafy.tott.member.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_INVALID_FIND_BY_ACCOUNT_NUMBER(400, "MEMBER_01", "잘못된 계좌 번호입니다."),
    ERROR_CLIENT_BY_NOT_EQUALS_BY_VALIDATION_MEMO(400, "MEMBER_02", "인증 번호가 잘못되었습니다."),
    ERROR_CLIENT_WITH_MEMBER_IS_NOT_EXISTED(400, "MEMBER_03", "존재하지 않는 회원입니다."),
    ERROR_CLIENT_BY_MEMBER_IS_EXISTED(400, "MEMBER_04", "이미 가입된 회원입니다."),
    ERROR_CLIENT_BY_PASSWORD_IS_NOT_SAME_VALID_PASSWORD(400, "MEMBER_05", "비밀번호가 일치하지 않습니다."),
    ERROR_CLIENT_BY_PASSWORD_IS_NOT_VALID(400, "MEMBER_06", "유효하지 않은 비밀번호 형식입니다."),
    ERROR_CLIENT_BY_EMAIL_IS_NOT_VALID(400, "MEMBER_07", "유효하지 않은 이메일 형식입니다."),
    ;
    private final int statusCode;
    private final String errorCode;
    private final String message;

    MemberErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
