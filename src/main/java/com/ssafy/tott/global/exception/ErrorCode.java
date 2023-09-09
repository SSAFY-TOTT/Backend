package com.ssafy.tott.global.exception;

public interface ErrorCode {
  int getStatusCode();

  String getErrorCode();

  String getMessage();
}
