package com.ssafy.tott.housedetail.data.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailStateResponse {
    private long budget;
    private long creditLine;

    public static HouseDetailStateResponse of(long budget, long creditLine) {
        return HouseDetailStateResponse.builder()
                .budget(budget)
                .creditLine(creditLine)
                .build();
    }
}
