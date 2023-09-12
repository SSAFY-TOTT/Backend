package com.ssafy.tott.housegeo.domain.query;

import com.ssafy.tott.housegeo.data.cond.HouseGeoFilterCond;
import com.ssafy.tott.housegeo.domain.HouseGeo;

import java.util.List;

public interface HouseGeoRepositoryCustom {
    List<HouseGeo> findByFilterCond(HouseGeoFilterCond cond);
}
