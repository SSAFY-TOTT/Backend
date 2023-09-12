package com.ssafy.tott.housedetail.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class HouseDetailException extends TOTTException {
    public HouseDetailException(ErrorCode code) {
        super(code);
    }
}
