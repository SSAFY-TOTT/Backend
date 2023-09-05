package com.ssafy.tott.housedetail.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDetailRepository extends JpaRepository<HouseDetail, Integer> {
}
