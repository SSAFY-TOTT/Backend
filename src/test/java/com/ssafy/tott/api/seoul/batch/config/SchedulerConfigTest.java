package com.ssafy.tott.api.seoul.batch.config;

import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = {"test"})
public class SchedulerConfigTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;

    @DisplayName("전세집 자료 DB 저장 Test")
    @Test
    void saveRentHouseToDB(){
        Awaitility.await()
                .atMost(2, TimeUnit.MINUTES)
                        .untilAsserted(()->{
                                List<HouseDetail> houses = houseDetailRepository.findAll();
                                assertEquals(100,houses.size());
                        });

//        assertDoesNotThrow(() -> schedulerConfig.runHouseDataJob());
    }
}
