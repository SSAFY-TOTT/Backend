package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.mapper.HouseDetailMapper;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HouseDetailService {
    private final HouseDetailRepository houseDetailRepository;
    private final HouseDetailMapper houseDetailMapper;

    /**
     * HouseDetail 데이터를 저장한다.
     *
     * @param row      row 참조
     * @param houseGeo houseGeo 참조
     */
    public void saveHouseDetail(RentRow row, HouseGeo houseGeo) {
        houseDetailRepository.save(houseDetailMapper.toEntity(row, houseGeo));
    }
}
