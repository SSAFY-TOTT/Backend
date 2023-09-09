package com.ssafy.tott.api.seoul.service;

import com.ssafy.tott.api.seoulopendata.service.SeoulOpenDataService;
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
class SeoulOpenDataServiceTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;

    @Autowired
    private SeoulOpenDataService seoulOpenDataService;

    @DisplayName("전세집 자료 DB 저장 Test")
    @Test
    void saveRentHouseToDB() {
        assertDoesNotThrow(() -> seoulOpenDataService.fetchHouseData(10000));

        List<HouseDetail> houseDetails = houseDetailRepository.findAll();
        assertTrue(houseDetails.size() >= 10);
    }
}
