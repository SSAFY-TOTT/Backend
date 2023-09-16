package com.ssafy.tott.housedetail.domain.query;

import com.ssafy.tott.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;

import java.util.List;
import java.util.Optional;

public interface HouseDetailRepositoryCustom {
    List<HouseDetail> findByRecentViewCond(HouseDetailRecentViewCond cond);

    Optional<HouseDetail> findByDataCond(ExistByDetailCond cond);
}
