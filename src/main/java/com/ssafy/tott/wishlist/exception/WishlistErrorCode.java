package com.ssafy.tott.wishlist.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum WishlistErrorCode implements ErrorCode {
    ERROR_CLIENT_WITH_WISHLIST_IS_FULL(400, "WISHLIST_01", "위시리스트가 가득 찼습니다."),
    ERROR_CLIENT_WITH_WISHLIST_IS_NOT_EXISTED(400, "WISHLIST_02", "존재하지 않는 위시리스트 입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    WishlistErrorCode(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
