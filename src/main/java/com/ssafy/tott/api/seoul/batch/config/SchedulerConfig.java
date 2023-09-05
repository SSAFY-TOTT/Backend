package com.ssafy.tott.api.seoul.batch.config;

import com.ssafy.tott.api.seoul.data.RentApiModel;
import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.api.seoul.module.HouseAPI;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.service.HouseGeoService;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.service.RegionService;
import io.micrometer.core.instrument.MultiGauge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {
    private final HouseAPI houseAPI;
    private final RegionService regionService;
    private final HouseGeoService houseGeoService;
    private final HouseDetailService houseDetailService;

//    @Transactional
    @Scheduled(cron = "${schedules.cron.reward.publish}")/* 01:00 Start*/
    void runHouseDataJob() {
        RentApiModel totalCountResponse = houseAPI.fetchAPI(1, 1);
        int totalCount = totalCountResponse.getTbLnOpendataRentV().getListTotalCount();
        fetchHouseData(totalCount);
    }


    private void fetchHouseData(int totalCount) {
        for (int i = 1; i < totalCount / 2000; i += 999) {
            try {
                RentApiModel rentApiModel = houseAPI.fetchAPI(i, i + 999);
                save(rentApiModel);
                log.info("fetchHouseData buffer " + i / (totalCount / 2000) + "%");
            } catch (Exception ignored) {
            }
        }
    }

    @Transactional
    void save(RentApiModel rentApiModel){
        for (RentRow row : rentApiModel.getTbLnOpendataRentV().getRow()) {
            Region region = regionService.getRegion(row);
            HouseGeo houseGeo = houseGeoService.getHouseGeo(row, region);
            houseDetailService.saveHouseDetail(row, houseGeo);
        }
    }
}
