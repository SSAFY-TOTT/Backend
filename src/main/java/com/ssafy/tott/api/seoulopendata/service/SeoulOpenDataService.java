package com.ssafy.tott.api.seoulopendata.service;

import com.ssafy.tott.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.tott.api.seoulopendata.data.dto.request.RentAPIRequest;
import com.ssafy.tott.api.seoulopendata.data.dto.response.RentAPIResponse;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.service.HouseGeoService;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SeoulOpenDataService {

    private final RegionService regionService;
    private final HouseGeoService houseGeoService;
    private final HouseDetailService houseDetailService;
    private final SeoulOpenDataRentHouseFetchAPI seoulOpenDataRentHouseAPI;

    /**
     * 전세집 데이터를 추출 및 DB에 저장한다 .
     *
     * @param devide HouseData를 어느 정도로 줄인건지에 대한 param
     */
    @Transactional
    public void fetchHouseData(int devide) {
        log.info("fetchHouseData method start");
        RentAPIResponse healthCheckResponse =
                (RentAPIResponse) seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.healthCheckRequest());
        int totalCount = healthCheckResponse.getTbLnOpendataRentV().getListTotalCount() / devide;

        for (int i = 1; i < totalCount; i += 999) {
            RentAPIResponse rentAPIResponse =
                    (RentAPIResponse) seoulOpenDataRentHouseAPI.fetchAPI(RentAPIRequest.toRequest(i, 999));
            saveHouseData(seoulOpenDataRentHouseAPI.filteringRentHouse(rentAPIResponse));
            log.info("data search {}%", (float) i / totalCount * 100);
        }
        log.info("fetchHouseData method end");
    }

    /**
     * 전세집 데이터를 DB에 저장한다. 저장 테이블은 region, houseGeo, houseDetail 이다.
     *
     * @param rentRows 공공데이터에서 뽑은 전세집 데이터들
     */
    private void saveHouseData(List<RentRow> rentRows) {
        for (RentRow row : rentRows) {
            try {
                Region region = regionService.getRegion(row);
                HouseGeo houseGeo = houseGeoService.getHouseGeo(row, region);
                Optional<HouseDetail> optionalHouseDetail =
                        houseDetailService.findByDataCond(ExistByDetailCond.from(row));
                if (optionalHouseDetail.isPresent()) {
                    optionalHouseDetail.get().modifyUpdateDateByNow();
                    continue;
                }
                houseDetailService.saveHouseDetail(row, houseGeo);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }
}
