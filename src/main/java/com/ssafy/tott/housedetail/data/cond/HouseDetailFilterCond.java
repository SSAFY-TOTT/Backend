package com.ssafy.tott.housedetail.data.cond;

import com.ssafy.tott.housegeo.domain.BuildingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseDetailFilterCond {
    private String districtName;
    private String legalDongName;
    private int minPrice;
    private int maxPrice;
    private int minArea;
    private int maxArea;
    private List<BuildingType> type;
    private int buildingYear;

    @Builder
    public HouseDetailFilterCond(String districtName, String legalDongName, int minPrice, int maxPrice, int minArea, int maxArea, List<BuildingType> type, int buildingYear) {
        this.districtName = districtName;
        this.legalDongName = legalDongName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.type = type;
        this.buildingYear = buildingYear;
    }
}
