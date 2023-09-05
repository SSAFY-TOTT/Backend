package com.ssafy.tott.housegeo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseGeoRepository extends JpaRepository<HouseGeo, Integer> {
    Optional<HouseGeo> findByMainNumberAndSubNumber(int mainNumber,int subNumber);
}
