package com.ssafy.tott.housegeo.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.housegeo.domain.BuildingType;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles(profiles = {"test"})
@Transactional
@DataJpaTest
class HouseGeoRepositoryTest {
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
  @Autowired private HouseGeoRepository houseGeoRepository;
  @Autowired private RegionRepository regionRepository;
  private Region region;
  private HouseGeo houseGeo;

  @BeforeEach
  void setUp() {
    region =
        regionRepository.save(
            Region.builder()
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .build());
    houseGeo =
        houseGeoRepository.save(
            HouseGeo.builder()
                .mainNumber(Integer.parseInt(row.getBobn()))
                .buildingType(BuildingType.연립다세대)
                .subNumber(Integer.parseInt(row.getBubn()))
                .longitude(0)
                .latitude(0)
                .buildingName(row.getBldgNm())
                .region(region)
                .build());
  }

  @DisplayName("houseGeo 찾기")
  @Test
  void findHouseGeoWhenExist() {
    Optional<HouseGeo> findHouseGeo =
        houseGeoRepository.findByMainNumberAndSubNumber(
            Integer.parseInt(row.getBobn()), Integer.parseInt(row.getBubn()));
    assertTrue(findHouseGeo.isPresent());
    assertAll(
        () -> assertEquals(houseGeo.getLatitude(), findHouseGeo.get().getLatitude()),
        () -> assertEquals(houseGeo.getLongitude(), findHouseGeo.get().getLongitude()));
  }
}
