package com.ssafy.tott.api.seoul.batch.config;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
@Slf4j
public class SchedulerConfigTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;

    @Autowired
    private SchedulerConfig schedulerConfig;

    @DisplayName("전세집 자료 DB 저장 Test")
    @Test
    void saveRentHouseToDB(){
        assertDoesNotThrow(() -> schedulerConfig.runHouseDataJob());
        List<HouseDetail> houseDetails = houseDetailRepository.findAll();
        log.info("houseSize : [{}]", houseDetails.size());
        assertTrue(houseDetails.size() >= 100);
    }
}
