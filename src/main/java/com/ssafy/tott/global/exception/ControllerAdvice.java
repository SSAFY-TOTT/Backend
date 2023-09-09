package com.ssafy.tott.global.exception;

import com.ssafy.tott.global.exception.dto.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler(TOTTException.class)
  public ResponseEntity<ExceptionResponse> handleException(TOTTException e) {
    return ResponseEntity.status(e.getStatusCode()).body(ExceptionResponse.from(e));
  }
}
