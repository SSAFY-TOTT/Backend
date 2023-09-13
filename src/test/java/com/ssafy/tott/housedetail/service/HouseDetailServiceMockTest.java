package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.config.MockitoTest;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.fixture.HouseDetailFixture;
import com.ssafy.tott.housedetail.fixture.RentRowFixture;
import com.ssafy.tott.housedetail.mapper.HouseDetailMapper;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.fixture.HouseGeoFixture;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.fixture.RegionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class HouseDetailServiceMockTest extends MockitoTest {

    private final RentRow row = RentRowFixture.RENT_ROW_ONE.toRentRow();
    @InjectMocks
    private HouseDetailService houseDetailService;
    @Mock
    private HouseDetailRepository houseDetailRepository;

    @Spy
    private HouseDetailMapper houseDetailMapper;

    @DisplayName("house detail 저장 테스트")
    @Test
    void houseDetailSaveTest() {
        // given
        Region region = RegionFixture.REGION_ONE.toRegion(row);
        HouseGeo houseGeo = HouseGeoFixture.CREATE_HOUSE_GEO.toHouseGeo(region);
        HouseDetail houseDetail = HouseDetailFixture.CREATE_HOUSE_DETAIL.toHouseDetail(houseGeo, row);
        given(houseDetailRepository.save(any())).willReturn(houseDetail);

        // when,then
        assertDoesNotThrow(() -> houseDetailService.saveHouseDetail(row, houseGeo));
    }
}
