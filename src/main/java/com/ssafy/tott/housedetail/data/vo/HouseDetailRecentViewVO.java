package com.ssafy.tott.housedetail.data.vo;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.region.domain.Region;
import lombok.*;

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
    private int floor;

    public static HouseDetailRecentViewVO from(HouseDetail detail) {
        HouseGeo geo = detail.getHouseGeo();
        Region region = geo.getRegion();
        return HouseDetailRecentViewVO.builder()
                .houseDetailId(detail.getId())
                .address(region.getDistrictName() + " " + region.getLegalDongName())
                .lat(geo.getLatitude())
                .lng(geo.getLongitude())
                .buildingName(geo.getBuildingName())
                .builtYear(geo.getConstructionYear())
                .area((int) (detail.getArea() / 3.3D))
                .price(detail.getPrice())
                .floor(detail.getFloor())
                .build();
    }
}
