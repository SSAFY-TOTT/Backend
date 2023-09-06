package com.ssafy.tott.region.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByDistrictCodeAndLegalDongCode(int districtCode, int legalDongCode);
}
