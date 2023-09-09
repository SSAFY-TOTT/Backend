package com.ssafy.tott.global.exception;

import lombok.Getter;

@Getter
public class TOTTException extends RuntimeException {
  private final int statusCode;
  private final String errorCode;
  private final String message;

  public TOTTException(ErrorCode code) {
    this.statusCode = code.getStatusCode();
    this.errorCode = code.getErrorCode();
    this.message = code.getMessage();
  }
}
