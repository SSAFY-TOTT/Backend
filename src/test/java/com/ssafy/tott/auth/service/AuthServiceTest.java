package com.ssafy.tott.auth.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.ssafy.tott.auth.dto.request.LoginRequest;
import com.ssafy.tott.auth.dto.response.TokenResponse;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class AuthServiceTest {
  @Autowired AuthService authService;

  @Autowired MemberRepository memberRepository;

  @BeforeEach
  void setUp() {
    memberRepository.save(MemberFixture.JEONGUK.toMember());
  }

  @DisplayName("사용자가 로그인에 성공한다.")
  @Test
  void loginSuccessTest() {
    /* Given */
    LoginRequest request = MemberFixture.JEONGUK.toLoginRequest();

    /* When */
    TokenResponse response = authService.login(request);

    /* Then */
    assertThat(response).isNotNull();
  }
}
