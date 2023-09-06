package com.ssafy.tott.api.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum APIErrorCode implements ErrorCode {
    ERROR_SERVER_BY_JSON_PROCESSING(500, "API_01", "내부 서버 오류 입니다."),
    ERROR_SERVER_BY_OUTER_API_SERVER(500, "API_02", "API 서버 오류 입니다.");

    private int statusCode;
    private String errorCode;
    private String message;

    APIErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
