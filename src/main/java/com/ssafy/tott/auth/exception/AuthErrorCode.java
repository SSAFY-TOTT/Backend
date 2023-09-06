package com.ssafy.tott.auth.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum AuthErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_AUTHORIZATION_INFORMATION(400, "AUTH_01", "권한 정보가 없는 토큰입니다.");
    private int statusCode;
    private String errorCode;
    private String message;

    AuthErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
