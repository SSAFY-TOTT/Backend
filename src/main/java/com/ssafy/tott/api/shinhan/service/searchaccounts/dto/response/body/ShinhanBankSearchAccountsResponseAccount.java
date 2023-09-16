package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchAccountsResponseAccount {
    @JsonProperty("구분")
    private String accountType;

    @JsonProperty("계좌번호")
    private String accountNumber;

    @JsonProperty("잔액(통화별)")
    private String amount;

    /* 데이터 가공을 위한 계좌 정보 생성기 */
    public void initAccount() {
        accountNumber = String.format("%14d", randomNumberByLongType(100000000000000L));
        long randomAmount = randomNumberByLongType(100_000_000L);
        amount = String.valueOf(Math.max(randomAmount, 10_000_000 + randomAmount));
    }

    private Long randomNumberByLongType(Long bound) {
        return ThreadLocalRandom.current().nextLong(bound);
    }
}
