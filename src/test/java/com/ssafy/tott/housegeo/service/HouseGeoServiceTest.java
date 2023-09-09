package com.ssafy.tott.housegeo.service;

import com.ssafy.tott.api.kakao.data.vo.Documents;
import com.ssafy.tott.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.mapper.HouseGeoMapper;
import com.ssafy.tott.region.domain.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HouseGeoServiceTest {
    private final RentRow row =
            new RentRow(
                    "2023",
                    "11380",
                    "은평구",
                    "10300",
                    "불광동",
                    "1",
                    "대지",
                    "0105",
                    "0076",
                    3.0,
                    "20230901",
                    "전세",
                    57.76,
                    "23000",
                    "0",
                    "105-76",
                    "2018",
                    "연립다세대",
                    "",
                    "신규",
                    "",
                    "0",
                    "");
    private final Region region =
            Region.builder()
                    .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                    .legalDongName(row.getBjdongNm())
                    .districtCode(Integer.parseInt(row.getSggCd()))
                    .districtName(row.getSggNm())
                    .build();
    private final HouseGeo houseGeo =
            HouseGeo.builder()
                    .latitude(0)
                    .longitude(0)
                    .constructionYear(Integer.parseInt(row.getBuildYear()))
                    .buildingName(row.getBldgNm())
                    .mainNumber(Integer.parseInt(row.getBobn()))
                    .subNumber(Integer.parseInt(row.getBubn()))
                    .region(region)
                    .build();
    @InjectMocks
    private HouseGeoService houseGeoService;
    @Mock
    private HouseGeoRepository houseGeoRepository;
    @Mock
    private KakaoMapFetchAPI kakaoMapFetchAPI;
    @Spy
    private HouseGeoMapper houseGeoMapper;

    @DisplayName("houseGeo 가져오기")
    @Nested
    class getHouseGeoTest {
        @DisplayName("존재하는 houseGeo & 존재하는 좌표")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndExistedPositionTest() {
            // given
            given(
                    houseGeoRepository.findByMainNumberAndSubNumber(
                            Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.of(houseGeo));

            // when,then
            assertDoesNotThrow(() -> houseGeoService.getHouseGeo(row, region));
        }

        @DisplayName("존재하지않는 houseGeo & 존재하는 좌표")
        @Test
        void getHouseGeoWhenNotExistedHouseGeoAndExistedPositionTest() {
            // given
            given(
                    houseGeoRepository.findByMainNumberAndSubNumber(
                            Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(houseGeoRepository.save(any()))
                    .willReturn(
                            HouseGeo.builder()
                                    .latitude(0)
                                    .longitude(0)
                                    .constructionYear(Integer.parseInt(row.getBuildYear()))
                                    .buildingName(row.getBldgNm())
                                    .mainNumber(Integer.parseInt(row.getBobn()) + 1)
                                    .subNumber(Integer.parseInt(row.getBubn()) + 1)
                                    .region(region)
                                    .build());
            Documents documents = new Documents();
            documents.setX("127.123123");
            documents.setY("45.231231234");
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any())).willReturn(documents);
            // when, then
            assertDoesNotThrow(() -> houseGeoService.getHouseGeo(row, region));
        }

        @DisplayName("존재하지않는 houseGeo & 존재하지 않는 좌표")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndNotExistedPositionTest() {
            // given
            given(
                    houseGeoRepository.findByMainNumberAndSubNumber(
                            Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any()))
                    .willThrow(IndexOutOfBoundsException.class);

            // when,then
            assertThrows(IndexOutOfBoundsException.class, () -> houseGeoService.getHouseGeo(row, region));
        }
    }
}
