package com.ssafy.tott.housedetail.domain.query;

import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;

import java.util.List;

public interface HouseDetailRepositoryCustom {
    List<HouseDetail> findByRecentViewCond(HouseDetailRecentViewCond cond);
}
