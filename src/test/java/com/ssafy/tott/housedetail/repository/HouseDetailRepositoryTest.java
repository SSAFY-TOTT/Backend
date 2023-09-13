package com.ssafy.tott.housedetail.repository;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.fixture.RentRowFixture;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class HouseDetailRepositoryTest extends RepositoryTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private RentRow row;
    private Region region;
    private HouseGeo houseGeo;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        row = RentRowFixture.HOUSE_DETAIL_ONE.toRentRow();
        region = regionRepository.save(Region.from(row));
        /* TODO: 2023/09/13 추후 `houseGeo fixture`로 수정 */
        houseGeo = houseGeoRepository.save(HouseGeo.builder()
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .longitude(0)
                .latitude(0)
                .buildingName(row.getBldgNm())
                .region(region)
                .build());
        /* TODO: 2023/09/13 추후 `houseDetail fixture`로 수정 */
        houseDetail = HouseDetail.builder()
                .houseGeo(houseGeo)
                .floor(row.getFlrNo())
                .price(Integer.parseInt(row.getRentGtn()))
                .area(row.getRentArea())
                .build();
    }

    @DisplayName("집의 상세 정보 저장에 성공한다.")
    @Test
    void saveSuccess() {
        /* Given */
        /* When */
        HouseDetail savedHouseDetail = houseDetailRepository.save(houseDetail);

        /* Then */
        Assertions.assertThat(savedHouseDetail).isEqualTo(houseDetail);
    }
}
