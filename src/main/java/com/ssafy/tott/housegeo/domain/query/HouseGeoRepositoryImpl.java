package com.ssafy.tott.housegeo.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.housegeo.data.cond.HouseGeoFilterCond;
import com.ssafy.tott.housegeo.domain.HouseGeo;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.ssafy.tott.housedetail.domain.QHouseDetail.houseDetail;
import static com.ssafy.tott.housegeo.domain.QHouseGeo.houseGeo;
import static com.ssafy.tott.region.domain.QRegion.region;

public class HouseGeoRepositoryImpl implements HouseGeoRepositoryCustom {
    private final JPAQueryFactory query;

    public HouseGeoRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<HouseGeo> findByFilterCond(HouseGeoFilterCond cond) {
        return query.selectFrom(houseGeo)
                .distinct()
                .join(houseGeo.region, region)
                .join(houseGeo.houseDetailList, houseDetail).fetchJoin()
                .where(houseGeo.region.id.eq(cond.getRegionId()))
                .where(houseGeo.constructionYear.goe(cond.getBuildingYear()))
                .where(houseGeo.buildingType.in(cond.getTypes()))
                .where(houseDetail.area.between(cond.getMinArea(), cond.getMaxArea()))
                .where(houseDetail.price.between(cond.getMinPrice(), cond.getMaxPrice()))
                .where(houseDetail.updateDate.eq(LocalDate.now()))
                .fetch();
    }
}
