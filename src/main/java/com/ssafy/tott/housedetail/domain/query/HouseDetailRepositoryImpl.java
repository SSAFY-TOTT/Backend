package com.ssafy.tott.housedetail.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.api.seoulopendata.data.cond.ExistByDetailCond;
import com.ssafy.tott.housedetail.data.cond.HouseDetailRecentViewCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<HouseDetail> findByDataCond(ExistByDetailCond cond) {
        HouseDetail findHouseDetail = query.selectFrom(houseDetail)
                .join(houseDetail.houseGeo, houseGeo)
                .join(houseGeo.region, region)
                .where(region.districtCode.eq(cond.getDistrictCode())
                        .and(region.legalDongCode.eq(cond.getLegalDongCode()))
                        .and(houseGeo.mainNumber.eq(cond.getMainNumber()))
                        .and(houseGeo.subNumber.eq(cond.getSubNumber()))
                        .and(houseDetail.floor.eq(cond.getFloor()))
                        .and(houseDetail.area.eq(cond.getArea()))
                        .and(houseDetail.price.eq(cond.getPrice())))
                .fetchOne();
        return Optional.ofNullable(findHouseDetail);
    }
}
