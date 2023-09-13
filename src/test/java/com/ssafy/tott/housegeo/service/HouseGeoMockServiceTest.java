package com.ssafy.tott.housegeo.service;

import com.ssafy.tott.api.kakao.data.vo.Documents;
import com.ssafy.tott.api.kakao.service.KakaoMapFetchAPI;
import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.config.MockitoTest;
import com.ssafy.tott.housedetail.fixture.RentRowFixture;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.fixture.HouseGeoFixture;
import com.ssafy.tott.housegeo.mapper.HouseGeoMapper;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.fixture.RegionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class HouseGeoMockServiceTest extends MockitoTest {
    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    private final Region region = RegionFixture.REGION_ONE.toRegion(row);
    private final HouseGeo houseGeo = HouseGeoFixture.CREATE_HOUSE_GEO.toHouseGeoByRow(row, region);
    @InjectMocks
    private HouseGeoService houseGeoService;
    @Mock
    private HouseGeoRepository houseGeoRepository;
    @Mock
    private KakaoMapFetchAPI kakaoMapFetchAPI;
    @Spy
    private HouseGeoMapper houseGeoMapper;

    @DisplayName("houseGeo 조회")
    @Nested
    class getHouseGeoTest {
        @DisplayName("존재하는 좌표로 HouseGeo를 조회한다.")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndExistedPositionTest() {
            // given
            given(houseGeoRepository.findByMainNumberAndSubNumber(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.of(houseGeo));

            // when,then
            assertDoesNotThrow(() -> houseGeoService.getHouseGeo(row, region));
        }

        @DisplayName("존재하는 좌표로 존재하지 않는 HouseGeo를 조회시 예외를 던지지 않는다.")
        @Test
        void getHouseGeoWhenNotExistedHouseGeoAndExistedPositionTest() {
            // given
            given(houseGeoRepository.findByMainNumberAndSubNumber(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(houseGeoRepository.save(any()))
                    .willReturn(HouseGeo.builder()
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
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any()))
                    .willReturn(documents);

            // when, then
            assertDoesNotThrow(() -> houseGeoService.getHouseGeo(row, region));
        }

        @DisplayName("존재하지 않는 좌표로 HouseGeo 조회시 예외를 던진다.")
        @Test
        void getHouseGeoWhenExistedHouseGeoAndNotExistedPositionTest() {
            // given
            given(houseGeoRepository.findByMainNumberAndSubNumber(
                    Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn())))
                    .willReturn(Optional.empty());
            given(kakaoMapFetchAPI.kakaoAddressSearch(any(), any(), any(), any()))
                    .willThrow(IndexOutOfBoundsException.class);

            // when,then
            assertThrows(IndexOutOfBoundsException.class, () -> houseGeoService.getHouseGeo(row, region));
        }
    }
}
