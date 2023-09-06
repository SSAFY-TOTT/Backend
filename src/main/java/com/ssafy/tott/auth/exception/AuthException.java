package com.ssafy.tott.auth.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class AuthException extends TOTTException {
    public AuthException(ErrorCode code) {
        super(code);
    }
}
