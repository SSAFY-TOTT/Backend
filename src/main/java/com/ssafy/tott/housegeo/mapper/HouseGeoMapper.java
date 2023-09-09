package com.ssafy.tott.housegeo.mapper;

import com.ssafy.tott.api.kakao.data.Documents;
import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housegeo.domain.BuildingType;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.region.domain.Region;
import org.springframework.stereotype.Component;

@Component
public class HouseGeoMapper {
    public HouseGeo toEntity(RentRow row, Documents position, Region region) {
        return HouseGeo.builder()
                .latitude(Double.parseDouble(position.getY()))
                .longitude(Double.parseDouble(position.getX()))
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .buildingName(row.getBldgNm())
                .constructionYear(Integer.parseInt(row.getBuildYear()))
                .buildingType(BuildingType.valueOf(row.getHouseGbnNm()))
                .region(region)
                .build();
    }
}
