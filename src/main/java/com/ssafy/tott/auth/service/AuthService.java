package com.ssafy.tott.auth.service;

import com.ssafy.tott.auth.domain.AuthToken;
import com.ssafy.tott.auth.domain.AuthTokenRepository;
import com.ssafy.tott.auth.dto.request.LoginRequest;
import com.ssafy.tott.auth.dto.response.TokenResponse;
import com.ssafy.tott.auth.support.TokenProvider;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthTokenRepository authTokenRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;

    public TokenResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authentication = request.toAuthentication();
        Authentication authenticate =
                authenticationManagerBuilder.getObject().authenticate(authentication);

        TokenResponse response = tokenProvider.generateTokenResponse(authenticate);
        int memberId = Integer.parseInt(authenticate.getName());

        AuthToken token = AuthToken.builder().id(memberId).value(response.getRefreshToken()).build();
        authTokenRepository.save(token);

        memberService.updateCreditLine(memberId);

        return response;
    }
}
