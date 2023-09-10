package com.ssafy.tott.housedetail.data.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class HouseSearchDetailVO {
    private int houseDetailId;
    private int price;
    private int area;
    private int floor;
}
