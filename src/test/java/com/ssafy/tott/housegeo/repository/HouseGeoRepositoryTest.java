package com.ssafy.tott.housegeo.repository;

import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.fixture.HouseGeoFixture;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HouseGeoRepositoryTest extends RepositoryTest {

    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private HouseGeo houseGeo;

    @BeforeEach
    void setUp() {
        Region region = regionRepository.save(RegionFixture.CREATE_REGION_BY_ROW.toRegion());
        houseGeo = houseGeoRepository.save(HouseGeoFixture.GRAND_TOWER.toHouseGeo(region));
    }

    @DisplayName("본번과 부번으로 houseGeo 조회한다.")
    @Test
    void findByMainNumberAndSubNumberTest() {
        /* Given, When */
        Optional<HouseGeo> findHouseGeo = houseGeoRepository
                .findByMainNumberAndSubNumber(houseGeo.getMainNumber(), houseGeo.getSubNumber());

        /* Then */
        assertAll(
                () -> assertThat(findHouseGeo).isPresent(),
                () -> assertThat(findHouseGeo).contains(houseGeo)
        );
    }

    @DisplayName("필터 검색 조건으로 houseGeo 조회한다.")
    @Test
    void findByFilterCondTest() {
        /* TODO: 2023/09/13 houseDetail 필요 */
        /* Given */

        /* When */

        /* Then */

    }
}