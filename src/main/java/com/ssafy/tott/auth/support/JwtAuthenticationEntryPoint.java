package com.ssafy.tott.auth.support;

import com.ssafy.tott.auth.exception.AuthErrorCode;
import com.ssafy.tott.auth.exception.AuthException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) {
        resolver.resolveException(
                request,
                response,
                null,
                new AuthException(AuthErrorCode.ERROR_CLIENT_BY_AUTHENTICATED_MUST_BE_VALID));
    }
}
