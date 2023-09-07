package com.ssafy.tott.housedetail.service;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.region.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HouseDetailServiceTest {

    private final RentRow row = new RentRow("2023", "11380", "은평구", "10300", "불광동", "1", "대지", "0105", "0076", 3.0, "20230901", "전세", 57.76, "23000", "0", "105-76", "2018", "연립다세대", "", "신규", "", "0", "");
    @InjectMocks
    private HouseDetailService houseDetailService;
    @Mock
    private HouseDetailRepository houseDetailRepository;

    @Test
    void test() {
        //given
        Region region = Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build();
        HouseGeo houseGeo = HouseGeo.builder()
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .longitude(0)
                .latitude(0)
                .buildingName(row.getBldgNm())
                .region(region)
                .build();

        HouseDetail houseDetail = HouseDetail.builder().area(row.getRentArea()).price(Integer.parseInt(row.getRentGtn())).floor(row.getFlrNo()).houseGeo(houseGeo).build();
        given(houseDetailRepository.save(any())).willReturn(houseDetail);

        //when,then
        assertDoesNotThrow(() -> houseDetailService.saveHouseDetail(row, houseGeo));
    }

}
