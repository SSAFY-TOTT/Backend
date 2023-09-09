package com.ssafy.tott.housedetail.data.dto.request;

import com.ssafy.tott.housegeo.domain.BuildingType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseDetailFilterRequest {
    private String districtName;
    private String legalDongName;
    private int districtCode;
    private int legalDongCode;
    private int minPrice;
    private int maxPrice;
    private int minArea;
    private int maxArea;
    private List<BuildingType> types;
    private int buildingYear;
}
