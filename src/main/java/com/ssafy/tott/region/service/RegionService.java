package com.ssafy.tott.region.service;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegionService {
    private final RegionRepository regionRepository;

    /**
     * Region 데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     * @param row 공공데이터의 전세집 데이터
     * @return Region 참조
     */
    public Region getRegion(RentRow row){
        Optional<Region> region = regionRepository.findByDistrictCodeAndLegalDongCode(Integer.parseInt(row.getSggCd()),Integer.parseInt(row.getBjdongCd()));
        return region.orElseGet(() -> regionRepository.save(Region.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm()).build()));
    }
}
