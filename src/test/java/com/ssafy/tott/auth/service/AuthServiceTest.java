package com.ssafy.tott.auth.service;

import com.ssafy.tott.auth.dto.request.LoginRequest;
import com.ssafy.tott.auth.dto.response.TokenResponse;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AuthServiceTest extends ServiceTest {
    @Autowired
    AuthService authService;

    @BeforeEach
    void setUp() {
        saveMember(MemberFixture.SHINHAN);
    }

    @DisplayName("사용자가 로그인에 성공한다.")
    @Test
    void loginSuccessTest() {
        /* Given */
        LoginRequest request = MemberFixture.SHINHAN.toLoginRequest();

        /* When */
        TokenResponse response = authService.login(request);

        /* Then */
        assertThat(response).isNotNull();
    }
}
