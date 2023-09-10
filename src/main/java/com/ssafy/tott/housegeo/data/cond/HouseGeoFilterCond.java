package com.ssafy.tott.housegeo.data.cond;

import com.ssafy.tott.housegeo.data.dto.request.GeoSearchRequest;
import com.ssafy.tott.housegeo.domain.BuildingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseGeoFilterCond {
    private int regionId;
    private int minPrice;
    private int maxPrice;
    private double minArea;
    private double maxArea;
    private List<BuildingType> types;
    private int buildingYear;

    @Builder
    public HouseGeoFilterCond(
            int regionId, int minPrice, int maxPrice, double minArea, double maxArea,
            List<BuildingType> types, int buildingYear) {
        this.regionId = regionId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.types = types;
        this.buildingYear = buildingYear;
    }

    public static HouseGeoFilterCond of(int regionId, GeoSearchRequest request) {
        return HouseGeoFilterCond.builder()
                .regionId(regionId)
                .minArea(request.getMinArea() * 3.3D)
                .maxArea(request.getMaxArea() * 3.3D)
                .minPrice(request.getMinPrice() * 1000)
                .maxPrice(request.getMaxPrice() * 1000)
                .buildingYear(LocalDate.now().getYear() - request.getBuildingYear())
                .types(request.getTypes())
                .build();
    }
}
