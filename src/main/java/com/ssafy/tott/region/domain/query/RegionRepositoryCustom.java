package com.ssafy.tott.region.domain.query;

import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.Region;

import java.util.Optional;

public interface RegionRepositoryCustom {
    Optional<Region> findByFilterCond(RegionFilterCond cond);
}
