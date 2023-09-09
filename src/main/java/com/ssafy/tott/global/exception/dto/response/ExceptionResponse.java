package com.ssafy.tott.global.exception.dto.response;

import com.ssafy.tott.global.exception.TOTTException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ExceptionResponse {
  private String message;

  private ExceptionResponse(String message) {
    this.message = message;
  }

  public static ExceptionResponse from(TOTTException e) {
    return new ExceptionResponse(e.getMessage());
  }
}
