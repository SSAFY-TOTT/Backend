package com.ssafy.tott.wishlist.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tott.housegeo.domain.BuildingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WishlistVO {
    private int houseDetailId;
    private String address; // 자치구 + 법정동
    private String buildingName;
    private double lat;
    private double lng;
    private int area;
    private int price;
    private int builtYear;
    private int floor;
    private BuildingType buildingType;

    @Builder
    @QueryProjection
    public WishlistVO(int houseDetailId, String districtName, String legalDongName, String buildingName, double lat,
                      double lng, double area, int price, int builtYear, int floor, BuildingType type) {
        this.houseDetailId = houseDetailId;
        this.address = districtName + " " + legalDongName;
        this.buildingName = buildingName;
        this.lat = lat;
        this.lng = lng;
        this.area = (int) (area / 3.3D);
        this.price = price;
        this.builtYear = builtYear;
        this.floor = floor;
        this.buildingType = type;
    }
}
