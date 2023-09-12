package com.ssafy.tott.housedetail.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tott.housedetail.domain.QHouseDetail.houseDetail;
import static com.ssafy.tott.housegeo.domain.QHouseGeo.houseGeo;
import static com.ssafy.tott.region.domain.QRegion.region;

public class HouseDetailRepositoryImpl implements HouseDetailRepositoryCustom {
    private final JPAQueryFactory query;

    public HouseDetailRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<HouseDetail> findByRecentViewCond(HouseDetailRecentViewCond cond) {
        return query.selectFrom(houseDetail)
                .join(houseDetail.houseGeo, houseGeo).fetchJoin()
                .join(houseGeo.region, region).fetchJoin()
                .where(houseDetail.id.in(cond.getHousedetailIdList()))
                .fetch();
    }
}
