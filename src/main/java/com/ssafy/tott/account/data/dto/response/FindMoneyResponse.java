package com.ssafy.tott.account.data.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class FindMoneyResponse {
    private long budget;
    private long accountAmount;
    private long creditLine;

    public static FindMoneyResponse of(long budget, long accountAmount) {
        return FindMoneyResponse.builder()
                .budget(budget)
                .accountAmount(accountAmount)
                .creditLine(0L)
                .build();
    }

    public static FindMoneyResponse of(long budget, long accountAmount, long creditLine) {
        return FindMoneyResponse.builder()
                .budget(budget)
                .accountAmount(accountAmount)
                .creditLine(creditLine)
                .build();
    }
}
