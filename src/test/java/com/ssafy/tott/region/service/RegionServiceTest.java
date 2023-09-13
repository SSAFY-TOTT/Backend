package com.ssafy.tott.region.service;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.config.MockitoTest;
import com.ssafy.tott.housedetail.fixture.RentRowFixture;
import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.exception.RegionErrorCode;
import com.ssafy.tott.region.exception.RegionException;
import com.ssafy.tott.region.fixture.RegionFixture;
import com.ssafy.tott.region.mapper.RegionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RegionServiceTest extends MockitoTest {
    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    @InjectMocks
    private RegionService regionService;
    @Mock
    private RegionRepository regionRepository;
    @Spy
    private RegionMapper regionMapper;

    @DisplayName("Region를 조회한다.")
    @Nested
    class getRegionTest {
        @DisplayName("존재하는 Region을 조회한다.")
        @Test
        void getRegionWhenExistTest() {
            // given
            given(regionRepository.findByDistrictCodeAndLegalDongCode(
                    Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd())))
                    .willReturn(Optional.of(RegionFixture.REGION_ONE.toRegion(row)));

            // when, then
            assertDoesNotThrow(() -> regionService.getRegion(row));
        }

        @DisplayName("존재하지 않는 Region을 조회한다.")
        @Test
        void getRegionWhenNotExistTest() {
            // given
            given(regionRepository.findByDistrictCodeAndLegalDongCode(
                    Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd())))
                    .willReturn(Optional.empty());
            given(regionRepository.save(any()))
                    .willReturn(RegionFixture.REGION_ONE.toRegion(row));

            // when, then
            assertDoesNotThrow(() -> regionService.getRegion(row));
        }
    }

    @DisplayName("Region을 필터 조건으로 조회한다.")
    @Nested
    class FindByFilterCond {
        @DisplayName("필터 조건으로 Region찾기를 성공한다.")
        @Test
        void success() {
            /* Given */
            RegionFilterCond cond = RegionFixture.REGION_ONE.toCond();
            given(regionRepository.findByFilterCond(cond))
                    .willReturn(Optional.of(RegionFixture.REGION_ONE.toRegion()));

            /* When */
            /* Then */
            assertThatCode(() -> regionService.findByFilter(cond))
                    .doesNotThrowAnyException();
        }

        @DisplayName("필터 조건으로 Region찾기를 실패한다.")
        @Test
        void fail() {
            /* Given */
            RegionFilterCond cond = RegionFixture.REGION_ONE.toCond();
            given(regionRepository.findByFilterCond(cond))
                    .willReturn(Optional.empty());

            /* When */
            /* Then */
            assertThatThrownBy(() -> regionService.findByFilter(cond))
                    .isInstanceOf(RegionException.class)
                    .hasMessageContaining(RegionErrorCode.ERROR_CLIENT_BY_IS_NOT_EXISTED_REGION.getMessage());
        }
    }
}
