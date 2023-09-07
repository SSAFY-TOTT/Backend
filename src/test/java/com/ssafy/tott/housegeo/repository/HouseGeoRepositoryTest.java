package com.ssafy.tott.housegeo.repository;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles(profiles = {"test"})
@DataJpaTest
public class HouseGeoRepositoryTest {
    private final RentRow row = new RentRow("2023", "11380", "은평구", "10300", "불광동", "1", "대지", "0105", "0076", 3.0, "20230901", "전세", 57.76, "23000", "0", "105-76", "2018", "연립다세대", "", "신규", "", "0", "");
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private Region region;
    private HouseGeo houseGeo;

    @BeforeEach
    void setUp() {
        region = regionRepository.save(Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build());
        houseGeo = houseGeoRepository.save(HouseGeo.builder()
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .longitude(0)
                .latitude(0)
                .buildingName(row.getBldgNm())
                .region(region)
                .build());
    }

    @DisplayName("houseGeo 찾기")
    @Test
    void findHouseGeoWhenExist() {
        Optional<HouseGeo> findHouseGeo = houseGeoRepository.findByMainNumberAndSubNumber(Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn()));
        assertTrue(findHouseGeo.isPresent());
        assertEquals(houseGeo.getId(), houseGeo.getId());
    }
}
