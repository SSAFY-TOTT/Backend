package com.ssafy.tott.housegeo.domain;

import com.ssafy.tott.housegeo.domain.query.HouseGeoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseGeoRepository extends JpaRepository<HouseGeo, Integer>, HouseGeoRepositoryCustom {
    Optional<HouseGeo> findByMainNumberAndSubNumber(int mainNumber, int subNumber);
}
