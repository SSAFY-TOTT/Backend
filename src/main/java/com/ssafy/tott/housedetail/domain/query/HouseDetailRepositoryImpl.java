package com.ssafy.tott.housedetail.domain.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailFilterRequest;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housegeo.domain.BuildingType;
import com.ssafy.tott.region.domain.Region;

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
    public List<HouseDetail> findByFilterCond(HouseDetailFilterRequest cond) {
        /* TODO: 2023/09/09 지역 찾기 */
        Optional<Region> findRegion = Optional.ofNullable(query.selectFrom(region)
                .where(region.districtName.eq(cond.getDistrictName()))
                .where(region.legalDongName.eq(cond.getLegalDongName()))
                .fetchOne());

        if (findRegion.isEmpty()) {
            throw new RuntimeException();
        }

        return query.selectFrom(houseDetail)
                .join(houseDetail.houseGeo, houseGeo).fetchJoin()
                .join(houseGeo.region, region)
                .where(houseDetail.houseGeo.region.id.eq(findRegion.get().getId()))
                .where(getFilterPrice(cond))
                .where(getFilterArea(cond))
                .where(getFilterBuildingYear(cond.getBuildingYear()))
                .where(getFilterBuildingType(cond.getType()))
                .fetch();
    }

    private BooleanExpression getFilterArea(HouseDetailFilterRequest cond) {
        /* TODO: 2023/09/09 3.3 곱하기 */
        /* TODO: 2023/09/09 MaxArea 가 60평이면 그 이상 전체 조회 */
        return houseDetail.area.between(cond.getMinArea(), cond.getMaxArea());
    }

    private BooleanExpression getFilterPrice(HouseDetailFilterRequest cond) {
        /* TODO: 2023/09/09 천만원 단위 -> 만원 단위로*/
        /* TODO: 2023/09/09 MaxPrice 가 십억원이상이면 그 이상으로 조회 */
        return houseDetail.price.between(cond.getMinPrice(), cond.getMaxPrice());
    }

    private BooleanExpression getFilterBuildingYear(int buildingYear) {
        /* 제한 없음이면 -1 */
        return buildingYear != -1 ? houseDetail.houseGeo.constructionYear.loe(buildingYear) : null;
    }

    private BooleanExpression getFilterBuildingType(List<BuildingType> types) {
        return houseDetail.houseGeo.buildingType.in(types);
    }
}
