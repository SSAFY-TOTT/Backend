package com.ssafy.tott.member.service;

import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.domain.MemberVerificationCache;
import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.data.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.exception.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles(profiles = {"test"})
@Transactional
@SpringBootTest
class MemberVerificationServiceTest {

    @Autowired
    MemberVerificationService memberVerificationService;
    MemberVerificationCache cache;

    @BeforeEach
    void setUp() {
        MemberSignupRequest request = MemberFixture.JEONGUK.toMemberSignupRequest();
        cache = memberVerificationService.cachingBySignupRequest(request);
    }

    @DisplayName("사용자 정보를 임시저장소에 저장한다.")
    @Test
    void cachingBySignupRequestTest() {
        /* Given */
        MemberSignupRequest request = MemberFixture.SHINHAN.toMemberSignupRequest();

        /* When */
        /* Then */
        assertThatCode(() -> memberVerificationService.cachingBySignupRequest(request))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자로 구성된 4자리 무작위 문자열을 생성한다.")
    @Test
    void generateMemoTest() {
        /* Given */
        Pattern pattern = Pattern.compile("^\\d{4}$");
        String memo = cache.getMemo().substring(0, 4);

        /* When */
        /* Then */
        assertThat(pattern.matcher(memo).matches()).isTrue();
    }

    @DisplayName("사용자가 인증 번호 검증에 성공한다.")
    @Test
    void verificationSuccessTest() {
        /* Given */
        String accountNumber = cache.getAccountNumber();
        String memo = cache.getMemo().substring(0, 4);
        MemberVerificationRequest request = new MemberVerificationRequest(accountNumber, memo);

        /* When */
        /* Then */
        assertThatCode(() -> memberVerificationService.verification(request))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자가 인증 번호 검증에 실패한다.")
    @Test
    void verificationFailTest() {
        /* Given */
        String accountNumber = cache.getAccountNumber();
        String memo = cache.getMemo().substring(0, 3);
        MemberVerificationRequest request = new MemberVerificationRequest(accountNumber, memo);

        /* When */
        /* Then */
        assertThatThrownBy(() -> memberVerificationService.verification(request))
                .isInstanceOf(MemberException.class);
    }
}
