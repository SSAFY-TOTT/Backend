package com.ssafy.tott.housedetail.domain.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.housedetail.data.cond.HouseDetailFilterCond;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housegeo.domain.BuildingType;

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
    public List<HouseDetail> findByFilterCond(HouseDetailFilterCond cond) {
        return query.selectFrom(houseDetail)
                .join(houseDetail.houseGeo, houseGeo).fetchJoin()
                .join(houseGeo.region, region)
                .where(houseDetail.houseGeo.region.id.eq(cond.getRegionId()))
                .where(getFilterPrice(cond.getMinPrice(), cond.getMaxPrice()))
                .where(getFilterArea(cond.getMinArea(), cond.getMaxArea()))
                .where(getFilterBuildingYear(cond.getBuildingYear()))
                .where(getFilterBuildingType(cond.getTypes()))
                .fetch();
    }

    private BooleanExpression getFilterArea(double minArea, double maxArea) {
        return houseDetail.area.between(minArea, maxArea);
    }

    private BooleanExpression getFilterPrice(int minPrice, int maxPrice) {
        return houseDetail.price.between(minPrice, maxPrice);
    }

    private BooleanExpression getFilterBuildingYear(int buildingYear) {
        return houseDetail.houseGeo.constructionYear.goe(buildingYear);
    }

    private BooleanExpression getFilterBuildingType(List<BuildingType> types) {
        return houseDetail.houseGeo.buildingType.in(types);
    }
}
