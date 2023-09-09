package com.ssafy.tott.auth.support;

import com.ssafy.tott.auth.exception.AuthErrorCode;
import com.ssafy.tott.auth.exception.AuthException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final HandlerExceptionResolver resolver;

    public JwtAccessDeniedHandler(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) {
        resolver.resolveException(
                request,
                response,
                null,
                new AuthException(
                        AuthErrorCode.ERROR_CLIENT_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE));
    }
}
