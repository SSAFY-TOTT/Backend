package com.ssafy.tott.wishlist.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WishlistVO {
    private int id;
    private int price;
    private double area;
    private String buildingName;
    private String legalDongName;
    private String districtName;

    @Builder
    @QueryProjection
    public WishlistVO(int id, int price, double area, String buildingName, String legalDongName, String districtName) {
        this.id = id;
        this.price = price;
        this.area = area;
        this.buildingName = buildingName;
        this.legalDongName = legalDongName;
        this.districtName = districtName;
    }
}
