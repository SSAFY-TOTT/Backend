package com.ssafy.tott.api.seoul.service;

import com.ssafy.tott.api.seoul.data.RentApiModel;
import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.api.seoul.module.HouseAPI;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final RegionService regionService;
    private final HouseGeoService houseGeoService;
    private final HouseDetailService houseDetailService;
    private final HouseAPI houseAPI;

    @Transactional
    public void fetchHouseData(int devide) {
        int totalCount = houseAPI.fetchAPI(1, 1).getTbLnOpendataRentV().getListTotalCount();
        for (int i = 1; i < totalCount / devide; i += 999) {
            RentApiModel rentApiModel = houseAPI.fetchAPI(i, i + 999);
            saveHouseData(houseAPI.filteringRentHouse(rentApiModel));
            log.info("fetchHouseData buffer " + i / (totalCount / devide) + "%");
        }
    }

    private void saveHouseData(List<RentRow> rentRows) {
        for (RentRow row : rentRows) {
            Region region = regionService.getRegion(row);
            HouseGeo houseGeo = houseGeoService.getHouseGeo(row, region);
            houseDetailService.saveHouseDetail(row, houseGeo);
        }
    }
}
