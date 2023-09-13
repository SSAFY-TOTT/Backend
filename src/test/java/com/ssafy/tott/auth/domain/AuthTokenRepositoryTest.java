package com.ssafy.tott.auth.domain;

import com.ssafy.tott.global.config.RedisTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AuthTokenRepositoryTest extends RedisTest {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @DisplayName("회원 인증 토큰을 정상적으로 저장한다.")
    @Test
    void saveSuccess() {
        /* Given */
        AuthToken authToken = new AuthToken(1, "auth-token");

        /* When */
        AuthToken savedAuthToken = authTokenRepository.save(authToken);

        /* Then */
        assertThat(savedAuthToken).isEqualTo(authToken);
    }
}