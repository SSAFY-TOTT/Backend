package com.ssafy.tott.housedetail.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum HouseDetailErrorCode implements ErrorCode {
    ERROR_CLIENT_WITH_MEMBER_IS_NOT_EXISTED(400, "HOUSE_DETAIL_01", "존재하지 않는 집 상세정보 입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    HouseDetailErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
