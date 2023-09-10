package com.ssafy.tott.housegeo.data.vo;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class HouseSearchDetailVO {
    private int houseDetailId;
    private int price;
    private int area;
    private int floor;

    public static HouseSearchDetailVO from(HouseDetail detail) {
        return HouseSearchDetailVO.builder()
                .houseDetailId(detail.getId())
                .price(detail.getPrice())
                .area((int) Math.round(detail.getArea() / 3.3))
                .floor(detail.getFloor())
                .build();
    }
}
