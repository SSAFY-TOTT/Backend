package com.ssafy.tott.housegeo.service;

import com.ssafy.tott.api.kakao.data.vo.Documents;
import com.ssafy.tott.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.housegeo.data.cond.HouseGeoFilterCond;
import com.ssafy.tott.housegeo.data.dto.request.GeoSearchRequest;
import com.ssafy.tott.housegeo.data.dto.response.HouseGeoSearchResponse;
import com.ssafy.tott.housegeo.data.vo.HouseSearchGeoVO;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.mapper.HouseGeoMapper;
import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseGeoService {
    private final HouseGeoRepository houseGeoRepository;
    private final HouseGeoMapper houseGeoMapper;

    private final RegionService regionService;

    private final KakaoMapFetchAPI kakaoMapFetchAPI;

    /**
     * HouseGeo데이터를 가져온다. 만약 존재하지 않는다면 저장 후 해당 데이터를 가져온다.
     *
     * @param row    공공데이터의 전세집 데이터
     * @param region region 참조
     * @return HouseGeo 참조
     */
    public HouseGeo getHouseGeo(RentRow row, Region region) {
        Optional<HouseGeo> houseGeo =
                houseGeoRepository.findByMainNumberAndSubNumber(
                        Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn()));
        return houseGeo.orElseGet(
                () -> {
                    Documents position =
                            kakaoMapFetchAPI.kakaoAddressSearch(
                                    row.getSggNm(), row.getBjdongNm(), row.getBobn(), row.getBubn());
                    return houseGeoRepository.save(houseGeoMapper.toEntity(row, position, region));
                });
    }

    public HouseGeoSearchResponse findByFilter(GeoSearchRequest request) {
        Region findRegion = regionService.findByFilter(RegionFilterCond.from(request));
        List<HouseSearchGeoVO> houseGeoList = houseGeoRepository
                .findByFilterCond(HouseGeoFilterCond.of(findRegion.getId(), request))
                .stream().map(HouseSearchGeoVO::from)
                .collect(Collectors.toList());
        return HouseGeoSearchResponse.of(findRegion, houseGeoList);
    }
}
