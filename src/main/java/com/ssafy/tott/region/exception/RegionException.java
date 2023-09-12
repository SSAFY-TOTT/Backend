package com.ssafy.tott.region.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class RegionException extends TOTTException {
    public RegionException(ErrorCode code) {
        super(code);
    }
}
