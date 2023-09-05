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

    public Region getRegion(RentRow row){
        Optional<Region> region = regionRepository.findByDistrictCodeAndLegalDongCode(Integer.parseInt(row.getSggCd()),Integer.parseInt(row.getBjdongCd()));
        return region.orElseGet(() -> regionRepository.save(Region.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm()).build()));
    }
}
