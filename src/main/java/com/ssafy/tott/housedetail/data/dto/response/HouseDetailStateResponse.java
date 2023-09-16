package com.ssafy.tott.housedetail.data.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailStateResponse {
    private long amount;
    private long budget;
    private long creditLine;

    public static HouseDetailStateResponse of(long amount, long budget, long creditLine) {
        return HouseDetailStateResponse.builder()
                .amount(amount)
                .budget(budget)
                .creditLine(creditLine)
                .build();
    }

    public static HouseDetailStateResponse of(long amount, long budget) {
        return HouseDetailStateResponse.builder()
                .amount(amount)
                .budget(budget)
                .creditLine(0L)
                .build();
    }
}
