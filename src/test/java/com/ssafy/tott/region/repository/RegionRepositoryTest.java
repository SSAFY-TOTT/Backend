package com.ssafy.tott.region.repository;

import com.ssafy.tott.api.seoul.data.RentRow;
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
class RegionRepositoryTest {
    private final RentRow row = new RentRow("2023", "11380", "은평구", "10300", "불광동", "1", "대지", "0105", "0076", 3.0, "20230901", "전세", 57.76, "23000", "0", "105-76", "2018", "연립다세대", "", "신규", "", "0", "");
    @Autowired
    private RegionRepository regionRepository;
    private Region region;

    @BeforeEach
    void setUp() {
        region = regionRepository.saveAndFlush(Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build());
    }

    @DisplayName("존재하는 HouseDetail 찾기")
    @Test
    void findHouseDetailWhenExisted() {
        Optional<Region> findRegion = regionRepository.findByDistrictCodeAndLegalDongCode(Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd()));

        assertTrue(findRegion.isPresent());
        assertEquals(region.getId(), findRegion.get().getId());
    }
}
