package com.ssafy.tott.member.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class MemberException extends TOTTException {
    public MemberException(ErrorCode code) {
        super(code);
    }
}
