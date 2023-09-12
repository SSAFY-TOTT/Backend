package com.ssafy.tott.region.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum RegionErrorCode implements ErrorCode {
    ERROR_CLIENT_BY_IS_NOT_EXISTED_REGION(400, "REGION_01", "존재하지 않는 지역입니다.");

    private int statusCode;
    private String errorCode;
    private String message;

    RegionErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
