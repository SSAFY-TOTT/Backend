package com.ssafy.tott.housegeo.service;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.region.domain.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseGeoService {
    private final HouseGeoRepository houseGeoRepository;

    public HouseGeo getHouseGeo(RentRow row, Region region){
        int mainNumber = Integer.parseInt(row.getBobn());
        int subNumber = Integer.parseInt(row.getBubn());
        Optional<HouseGeo> houseGeo = houseGeoRepository.findByMainNumberAndSubNumber(mainNumber,subNumber);
        return houseGeo.orElseGet(() -> houseGeoRepository.save(HouseGeo.builder()
                .latitude(0)
                .longitude(0)
                .mainNumber(mainNumber)
                .subNumber(subNumber)
                .buildingName(row.getHouseGbnNm())
                .constructionYear(Integer.parseInt(row.getBuildYear()))
                .region(region)
                .build()));
    }
}
