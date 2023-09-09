package com.ssafy.tott.region.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.Region;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.ssafy.tott.region.domain.QRegion.region;

public class RegionRepositoryImpl implements RegionRepositoryCustom {
    private final JPAQueryFactory query;

    public RegionRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Region> findByFilterCond(RegionFilterCond cond) {
        Region findRegion = query.selectFrom(region)
                .where(region.districtCode.eq(cond.getDistrictCode()))
                .where(region.legalDongCode.eq(cond.getLegalDongCode()))
                .fetchOne();
        return Optional.ofNullable(findRegion);
    }
}
