package com.ssafy.tott.api.shinhan;

import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.ShinhanBankSearchAccountsResponse;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.ShinhanBankSearchNameResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.ShinhanBankTransfer1Response;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ShinhanBankAPITest extends ServiceTest {
    @Autowired
    private ShinhanBankAPI shinhanBankAPI;
    private MemberFixture memberFixture;

    @DisplayName("신한은행 1원 이체 API를 정상적으로 호출한다.")
    @Test
    void fetchTransfer1Test() {
        /* Given */
        MemberSignupRequest request = MemberFixture.SHINHAN.toMemberSignupRequest();

        /* When */
        ShinhanBankTransfer1Response response = shinhanBankAPI.fetchTransfer1API(
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
        ShinhanBankSearchNameResponse response =
                shinhanBankAPI.fetchSearchNameAPI(request.getBankCode(), request.getAccountNumber());

        /* Then */
        assertThat(response.isFailed()).isFalse();
    }

    @DisplayName("신한은행 계좌 목록 조회 API를 정상적으로 호출한다.")
    @Test
    void fetchAccountsTest() {
        /* Given */
        /* When */
        ShinhanBankSearchAccountsResponse response =
                shinhanBankAPI.fetchSearchAccountsAPI("encodeName");

        /* Then */
        assertThat(response.isFailed()).isFalse();
    }
}
