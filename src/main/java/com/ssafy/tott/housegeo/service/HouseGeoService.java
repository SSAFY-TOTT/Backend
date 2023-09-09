package com.ssafy.tott.housegeo.service;

import com.ssafy.tott.api.kakao.data.Documents;
import com.ssafy.tott.api.kakao.module.KakaoMapAPI;
import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.mapper.HouseGeoMapper;
import com.ssafy.tott.region.domain.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HouseGeoService {
    private final HouseGeoRepository houseGeoRepository;
    private final KakaoMapAPI kakaoMapAPI;
    private final HouseGeoMapper houseGeoMapper;

    /**
     * HouseGeo데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     *
     * @param row    공공데이터의 전세집 데이터
     * @param region region 참조
     * @return HouseGeo 참조
     */
    public HouseGeo getHouseGeo(RentRow row, Region region) {
        Optional<HouseGeo> houseGeo = houseGeoRepository.findByMainNumberAndSubNumber(Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn()));
        return houseGeo.orElseGet(() -> {
            Documents position = kakaoMapAPI.kakaoAddressSearch(row.getSggNm(), row.getBjdongNm(), row.getBobn(), row.getBubn());
            return houseGeoRepository.save(houseGeoMapper.toEntity(row, position, region));
        });
    }
}
