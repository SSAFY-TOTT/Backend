package com.ssafy.tott.housedetail.data.cond;

import lombok.AccessLevel;
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
    private List<Integer> type;
    private int buildingYear;
}
