package com.ssafy.tott.region.service;


import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.mapper.RegionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Transactional
@ActiveProfiles(profiles = {"test"})
@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {
    private final RentRow row = new RentRow("2023", "11380", "은평구", "10300", "불광동", "1", "대지", "0105", "0076", 3.0, "20230901", "전세", 57.76, "23000", "0", "105-76", "2018", "연립다세대", "", "신규", "", "0", "");
    @InjectMocks
    private RegionService regionService;
    @Mock
    private RegionRepository regionRepository;

    @Spy
    private RegionMapper regionMapper;

    @DisplayName("region 가져오기")
    @Nested
    class getRegionTest {
        @DisplayName("존재하는 region")
        @Test
        void getRegionWhenExistTest() {
            //given
            given(regionRepository.findByDistrictCodeAndLegalDongCode(Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd()))).willReturn(
                    Optional.of(Region.builder()
                            .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                            .legalDongName(row.getBjdongNm())
                            .districtCode(Integer.parseInt(row.getSggCd()))
                            .districtName(row.getSggNm())
                            .build())
            );

            //when, then
            assertDoesNotThrow(() -> regionService.getRegion(row));
        }

        @DisplayName("존재하지않는 region")
        @Test
        void getRegionWhenNotExistTest() {
            //given
            given(regionRepository.findByDistrictCodeAndLegalDongCode(Integer.parseInt(row.getSggCd()), Integer.parseInt(row.getBjdongCd()))).willReturn(
                    Optional.empty());
            given(regionRepository.save(any())).willReturn(Region.builder()
                    .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                    .legalDongName(row.getBjdongNm())
                    .districtCode(Integer.parseInt(row.getSggCd()))
                    .districtName(row.getSggNm())
                    .build());

            //when, then
            assertDoesNotThrow(() -> regionService.getRegion(row));
        }
    }
}
