package com.ssafy.tott.region.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByDistrictCodeAndLegalDongCode(int districtCode, int legalDongCode);
}
