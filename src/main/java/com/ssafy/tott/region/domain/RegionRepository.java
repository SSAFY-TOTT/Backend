package com.ssafy.tott.region.domain;

import com.ssafy.tott.region.domain.query.RegionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer>, RegionRepositoryCustom {
    Optional<Region> findByDistrictCodeAndLegalDongCode(int districtCode, int legalDongCode);
}
