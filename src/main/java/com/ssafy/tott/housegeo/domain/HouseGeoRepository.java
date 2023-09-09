package com.ssafy.tott.housegeo.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseGeoRepository extends JpaRepository<HouseGeo, Integer> {
  Optional<HouseGeo> findByMainNumberAndSubNumber(int mainNumber, int subNumber);
}
