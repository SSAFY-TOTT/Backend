package com.ssafy.tott.housegeo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseGeoRepository extends JpaRepository<HouseGeo, Integer> {
    Optional<HouseGeo> findByMainNumberAndSubNumber(int mainNumber,int subNumber);
}
