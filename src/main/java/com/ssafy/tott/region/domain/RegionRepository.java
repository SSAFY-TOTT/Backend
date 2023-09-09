package com.ssafy.tott.region.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
  Optional<Region> findByDistrictCodeAndLegalDongCode(int districtCode, int legalDongCode);
}
