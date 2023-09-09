package com.ssafy.tott.account.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import com.ssafy.tott.global.exception.TOTTException;

public class AccountException extends TOTTException {
  public AccountException(ErrorCode code) {
    super(code);
  }
}
