package com.ssafy.tott.housedetail.data.vo;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@AllArgsConstructor
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
    private boolean isWishlist; // 찜 목록 여부

    public static HouseDetailRecentViewVO from(HouseDetail detail) {
        return HouseDetailRecentViewVO.builder()
                .build();
    }
}
