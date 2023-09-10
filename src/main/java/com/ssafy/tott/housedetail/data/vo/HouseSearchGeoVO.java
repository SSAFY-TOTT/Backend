package com.ssafy.tott.housedetail.data.vo;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class HouseSearchGeoVO {
    private double lat;
    private double lng;
    private String districtName;
    private String legalDongName;
    private String buildingName;
    private int builtYear;
    private List<HouseSearchDetailVO> houseDetailList;
    private int houseDetailCount;

    @Builder
    private HouseSearchGeoVO(
            double lat, double lng, String districtName, String legalDongName, String buildingName,
            int builtYear, List<HouseSearchDetailVO> houseDetailList, int houseDetailCount) {
        this.lat = lat;
        this.lng = lng;
        this.districtName = districtName;
        this.legalDongName = legalDongName;
        this.buildingName = buildingName;
        this.builtYear = builtYear;
        this.houseDetailList = houseDetailList;
        this.houseDetailCount = houseDetailCount;
    }

    public static HouseSearchGeoVO from(HouseDetail houseDetail) {
        return null;
    }
}
