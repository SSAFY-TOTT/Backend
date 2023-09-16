package com.ssafy.tott.housedetail.data.vo;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailStateVO {
    private long creditLine;
    private long budget;

    public static HouseDetailStateVO of(long budget, long creditLine) {
        return HouseDetailStateVO.builder()
                .budget(budget)
                .creditLine(creditLine)
                .build();
    }
}
