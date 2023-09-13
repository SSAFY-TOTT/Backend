package com.ssafy.tott.region.repository;

import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.fixture.RegionFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RegionRepositoryTest extends RepositoryTest {
    @Autowired
    private RegionRepository regionRepository;
    private Region region;

    @BeforeEach
    void setUp() {
        region = RegionFixture.REGION_ONE.toRegion();
    }

    @DisplayName("지역을 정상적으로 저장한다.")
    @Test
    void saveSuccess() {
        /* Given */
        /* When */
        Region savedRegion = regionRepository.save(region);

        /* Then */
        assertThat(savedRegion).isEqualTo(region);
    }

    @DisplayName("자치구와 법정동코드가 존재하는 지역을 조회한다.")
    @Test
    void findByDistrictCodeAndLegalDongCodeSuccess() {
        /* Given */
        Region savedRegion = regionRepository.save(region);

        /* When */
        Optional<Region> optionalRegion =
                regionRepository.findByDistrictCodeAndLegalDongCode(
                        savedRegion.getDistrictCode(), savedRegion.getLegalDongCode());

        /* Then */
        assertThat(optionalRegion).isPresent();
    }

    @DisplayName("자치구와 법정동코드가 존재하는 지역을 조회에 실패한다.")
    @Test
    void findByDistrictCodeAndLegalDongCodeFail() {
        /* Given */
        regionRepository.save(region);
        int invalidDistrictCode = RegionFixture.REGION_THREE.getDistrictCode();
        int invalidLegalDongCode = RegionFixture.REGION_THREE.getLegalDongCode();

        /* When */
        Optional<Region> optionalRegion =
                regionRepository.findByDistrictCodeAndLegalDongCode(invalidDistrictCode, invalidLegalDongCode);

        /* Then */
        assertThat(optionalRegion).isEmpty();
    }

    @DisplayName("필터 조건으로 조회에 성공한다.")
    @Test
    void findByFilterCondSuccess() {
        /* Given */
        regionRepository.save(region);
        /* TODO: 2023/09/13 `region`과 연관이 있는 `geo`, `detail` 정보 저장 필요 */
        RegionFilterCond cond = RegionFixture.REGION_ONE.toCond();

        /* When */
        Optional<Region> optionalRegion = regionRepository.findByFilterCond(cond);

        /* Then */
        assertThat(optionalRegion).isPresent();
    }
}
