package com.ssafy.tott.housedetail.data.vo;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class HouseDetailRecentViewVO {
    private int houseDetailId;
    private String address; // 자치구 + 법정동
    private String buildingName;    // 건물 이름
    private double lat;
    private double lng;
    private int area;
    private int price;
    private int builtYear;
}
