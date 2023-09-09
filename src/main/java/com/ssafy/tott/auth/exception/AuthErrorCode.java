package com.ssafy.tott.auth.exception;

import com.ssafy.tott.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum AuthErrorCode implements ErrorCode {
  ERROR_CLIENT_BY_AUTHORIZATION_INFORMATION(400, "AUTH_01", "권한 정보가 없는 토큰입니다."),
  ERROR_CLIENT_BY_JWT_SIGNATURE_INVALID(401, "AUTH_02", "잘못된 서명입니다."),
  ERROR_CLINET_BY_JWT_KEY_EXPIERD(401, "AUTH_03", "만료된 토큰입니다."),
  ERROR_CLIENT_BY_JWT_NOT_SUPPORT(401, "AUTH_04", "지원하지 않는 토큰입니다."),
  ERROR_CLIENT_BY_JWT_KEY_INVALID(401, "AUTH_05", "잘못된 토큰입니다."),
  ERROR_CLIENT_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE(403, "AUTH_06", "해당 기능에 대한 권한이 없습니다."),
  ERROR_CLIENT_BY_AUTHENTICATED_MUST_BE_VALID(401, "AUTH_07", "사용자 인증이 필요합니다."),
  ;
  private int statusCode;
  private String errorCode;
  private String message;

  AuthErrorCode(int statusCode, String errorCode, String message) {
    this.statusCode = statusCode;
    this.errorCode = errorCode;
    this.message = message;
  }
}
