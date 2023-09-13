package com.ssafy.tott.api.seoul.service;

import com.ssafy.tott.api.seoulopendata.service.SeoulOpenDataService;
import com.ssafy.tott.global.config.ServiceTest;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeoulOpenDataServiceTest extends ServiceTest {
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
