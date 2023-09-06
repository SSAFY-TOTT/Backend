package com.ssafy.tott.member.service;

import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.request.MemberVerificationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatCode;

@ActiveProfiles(profiles = {"test"})
@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberVerificationService memberVerificationService;

    @DisplayName("회원이 정상적으로 회원가입한다. ")
    @Test
    void signupTest() {
        /* Given */
        MemberSignupRequest request = MemberFixture.JEONGUK.toMemberSignupRequest();

        /* When */
        /* Then */
        assertThatCode(() -> memberService.signup(request))
                .doesNotThrowAnyException();
    }

    @DisplayName("회원이 정상적으로 인증한다.")
    @Test
    void verificationTest() {
        /* Given */
        MemberVerificationCache memberVerificationCache =
                memberVerificationService.cachingBySignupRequest(MemberFixture.JEONGUK.toMemberSignupRequest());
        MemberVerificationRequest memberVerificationRequest =
                new MemberVerificationRequest(memberVerificationCache.getAccountNumber(), memberVerificationCache.getMemo().substring(0, 4));

        /* When */
        /* Then */
        assertThatCode(() -> memberService.verification(memberVerificationRequest))
                .doesNotThrowAnyException();
    }
}