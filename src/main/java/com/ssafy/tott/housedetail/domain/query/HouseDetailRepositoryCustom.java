package com.ssafy.tott.housedetail.domain.query;

import com.ssafy.tott.housedetail.data.cond.HouseDetailFilterCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;

import java.util.List;

public interface HouseDetailRepositoryCustom {
    List<HouseDetail> findByFilterCond(HouseDetailFilterCond cond);
}
