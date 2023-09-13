package com.ssafy.tott.housedetail.repository;

import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.fixture.HouseDetailFixture;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class HouseDetailRepositoryTest extends RepositoryTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private HouseGeo houseGeo;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        houseGeo = houseGeoRepository.save(HouseGeoFixture.JU_GONG4.toHouseGeo(region));
    }

    @DisplayName("집의 상세 정보를 저장한다.")
    @Test
    void saveSuccess() {
        /* Given */
        houseDetail = HouseDetailFixture.JU_GONG4_1.toHouseDetail(houseGeo);

        /* When */
        HouseDetail savedHouseDetail = houseDetailRepository.save(houseDetail);

        /* Then */
        assertThat(savedHouseDetail).isEqualTo(houseDetail);
    }

    @DisplayName("houseDetail의 식별자로 조회한다.")
    @Test
    void findByIdSuccess() {
        /* Given */
        houseDetail = HouseDetailFixture.JU_GONG4_1.toHouseDetail(houseGeo);
        HouseDetail savedHouseDetail = houseDetailRepository.save(houseDetail);

        /* When */
        Optional<HouseDetail> optionalHouseDetail = houseDetailRepository.findById(savedHouseDetail.getId());

        /* Then */
        assertThat(optionalHouseDetail).isPresent();
    }

    @DisplayName("최근 본 집을 조회한다.")
    @Test
    void findByRecentViewCondSuccess() {
        /* Given */
        /* 조회 O */
        HouseDetail savedHouseDetail1 =
                houseDetailRepository.save(HouseDetailFixture.JU_GONG4_1.toHouseDetail(houseGeo));
        HouseDetail savedHouseDetail2 =
                houseDetailRepository.save(HouseDetailFixture.JU_GONG4_6.toHouseDetail(houseGeo));

        /* 조회 X */
        houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(houseGeo));

        /* When */
        List<HouseDetail> findHouseDetails = houseDetailRepository.findByRecentViewCond(
                new HouseDetailRecentViewCond(
                        List.of(savedHouseDetail1.getId(), savedHouseDetail2.getId())));

        /* Then */
        assertThat(findHouseDetails).hasSize(2);
    }
}
