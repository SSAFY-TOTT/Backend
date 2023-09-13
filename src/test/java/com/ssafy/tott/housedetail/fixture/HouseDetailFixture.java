package com.ssafy.tott.housedetail.fixture;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum HouseDetailFixture {
    GRAND_TOWER_3(25200, 29.98, 3),
    JU_GONG4_1(35000, 59.39, 1),
    JU_GONG4_6(21000, 45.77, 6),
    SAN_DO_3(9372, 41.14, 3);
    private int price;
    private double area;
    private int floor;

    public HouseDetail toHouseDetail(HouseGeo geo) {
        return HouseDetail.builder()
                .houseGeo(geo)
                .price(price)
                .area(area)
                .floor(floor)
                .build();
    }
}
