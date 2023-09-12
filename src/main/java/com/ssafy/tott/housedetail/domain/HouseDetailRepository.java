package com.ssafy.tott.housedetail.domain;

import com.ssafy.tott.housedetail.domain.query.HouseDetailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseDetailRepository extends JpaRepository<HouseDetail, Integer>, HouseDetailRepositoryCustom {
}
