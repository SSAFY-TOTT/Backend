package com.ssafy.tott.api.shinhan;

import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ShinhanBankAPITest {
    @Autowired
    private ShinhanBankAPI shinhanBankAPI;
    private MemberFixture memberFixture;

    @DisplayName("신한은행 1원 이체 API를 정상적으로 호출한다.")
    @Test
    void fetchTransfer1Test() {
        /* Given */
        MemberSignupRequest request = MemberFixture.SHINHAN.toMemberSignupRequest();

        /* When */
        ShinhanBankAPIResponse response =
                shinhanBankAPI.fetchTransfer1API(
                        request.getBankCode(), request.getAccountNumber(), "1234 전세역전");

        /* Then */
        assertThat(response.isFailed()).isFalse();
    }

    @DisplayName("신한은행 예금주 실명 조회 API를 정상적으로 호출한다.")
    @Test
    void fetchSearchNameTest() {
        /* Given */
        MemberSignupRequest request = MemberFixture.SHINHAN.toMemberSignupRequest();

        /* When */
        ShinhanBankAPIResponse response =
                shinhanBankAPI.fetchSearchNameAPI(request.getBankCode(), request.getAccountNumber());

        /* Then */
        assertThat(response.isFailed()).isFalse();
    }
}
