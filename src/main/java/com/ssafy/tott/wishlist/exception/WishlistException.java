package com.ssafy.tott.wishlist.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class WishlistException extends TOTTException {
    public WishlistException(ErrorCode code) {
        super(code);
    }
}
