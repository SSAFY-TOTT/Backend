package com.ssafy.tott.housegeo.data.vo;

import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class HouseSearchGeoVO {
    private double lat;
    private double lng;
    private String buildingName;
    private int builtYear;
    private List<HouseSearchDetailVO> houseDetailList;
    private int houseDetailCount;

    public static HouseSearchGeoVO from(HouseGeo geo) {
        List<HouseSearchDetailVO> detailList = getHouseDetailList(geo);
        return HouseSearchGeoVO.builder()
                .lat(geo.getLatitude())
                .lng(geo.getLongitude())
                .buildingName(geo.getBuildingName())
                .builtYear(geo.getConstructionYear())
                .houseDetailList(detailList)
                .houseDetailCount(detailList.size())
                .build();
    }

    private static List<HouseSearchDetailVO> getHouseDetailList(HouseGeo geo) {
        return geo.getHouseDetailList().stream()
                .map(HouseSearchDetailVO::from)
                .collect(Collectors.toList());
    }
}
