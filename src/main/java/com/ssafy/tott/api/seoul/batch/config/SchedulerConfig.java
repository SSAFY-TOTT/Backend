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
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
    private final HouseAPI houseAPI;
    private final RegionService regionService;
    private final HouseGeoService houseGeoService;
    private final HouseDetailService houseDetailService;

    @Scheduled(cron = "${schedules.cron.reward.publish}")
    public void runHouseDataJob() {
        List<RentRow> rentRows = fetchHouseData();

        for(int i = 0; i < 1;i++){
            RentRow row = rentRows.get(i);
            Region region = regionService.getRegion(row);
            HouseGeo houseGeo = houseGeoService.getHouseGeo(row,region);
            houseDetailService.saveHouseDetail(row,houseGeo);
        }
    }


    private List<RentRow> fetchHouseData() {
        List<RentRow> rentRows = new ArrayList<>();
        for(int i = 1; rentRows.size() < 2; i+=5){
            try {
                RentApiModel rentApiModel = houseAPI.fetchAPI(i, i + 5);
                rentRows.addAll(houseAPI.filteringRentHouse(rentApiModel));
            }catch(Exception ignored){ }
        }
        return rentRows;
    }
}
