package com.ssafy.tott.housegeo.fixture;

import com.ssafy.tott.housegeo.domain.BuildingType;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.region.domain.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum HouseGeoFixture {
    GRAND_TOWER(37.4869292125081, 126.92967594474, 1425, 7, 2016, "그랜드타워", BuildingType.아파트),
    JU_GONG4(37.6553473821348, 127.076921363501, 356, 2, 1991, "주공4", BuildingType.아파트),
    SAM_DO(37.5624883732543, 127.163924681649, 233, 10, 1986, "삼도", BuildingType.연립다세대);
    private double longitude;
    private double latitude;
    private int mainNumber;
    private int subNumber;
    private int constructionYear;
    private String buildingName;
    private BuildingType buildingType;

    public HouseGeo toHouseGeo(Region region) {
        return HouseGeo.builder()
                .region(region)
                .longitude(longitude)
                .latitude(latitude)
                .mainNumber(mainNumber)
                .subNumber(subNumber)
                .constructionYear(constructionYear)
                .buildingName(buildingName)
                .buildingType(buildingType)
                .build();
    }
}
