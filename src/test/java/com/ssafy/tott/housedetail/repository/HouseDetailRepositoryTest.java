package com.ssafy.tott.housedetail.repository;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"test"})
@DataJpaTest
public class HouseDetailRepositoryTest {
    private final RentRow row = new RentRow("2023", "11380", "은평구", "10300", "불광동", "1", "대지", "0105", "0076", 3.0, "20230901", "전세", 57.76, "23000", "0", "105-76", "2018", "연립다세대", "", "신규", "", "0", "");
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private Region region;
    private HouseGeo houseGeo;
    private HouseDetail houseDetail;

    @BeforeEach
    void setUp() {
        region = regionRepository.save(Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build());
        houseGeo = houseGeoRepository.save(HouseGeo.builder()
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .longitude(0)
                .latitude(0)
                .buildingName(row.getBldgNm())
                .region(region)
                .build());
        houseDetail = houseDetailRepository.save(HouseDetail.builder()
                .houseGeo(houseGeo)
                .floor(row.getFlrNo())
                .price(Integer.parseInt(row.getRentGtn()))
                .area(row.getRentArea())
                .build());
    }
}
