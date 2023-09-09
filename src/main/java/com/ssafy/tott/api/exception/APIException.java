package com.ssafy.tott.api.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class APIException extends TOTTException {
    public APIException(ErrorCode code) {
        super(code);
    }
}
