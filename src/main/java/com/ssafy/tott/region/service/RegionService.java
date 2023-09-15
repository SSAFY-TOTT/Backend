package com.ssafy.tott.region.service;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.data.vo.DistrictVO;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.exception.RegionErrorCode;
import com.ssafy.tott.region.exception.RegionException;
import com.ssafy.tott.region.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    /**
     * Region 데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     *
     * @param row 공공데이터의 전세집 데이터
     * @return Region 참조
     */
    public Region getRegion(RentRow row) {
        Optional<Region> region =
                regionRepository.findByDistrictCodeAndLegalDongCode(
                        Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd()));
        return region.orElseGet(() -> regionRepository.save(regionMapper.toEntity(row)));
    }

    public Region findByFilter(RegionFilterCond cond) {
        return regionRepository.findByFilterCond(cond)
                .orElseThrow(() -> new RegionException(RegionErrorCode.ERROR_CLIENT_BY_IS_NOT_EXISTED_REGION));
    }

    public List<DistrictVO> findDistrictAll() {
        return regionRepository.findAllToDistrict();
    }
}
