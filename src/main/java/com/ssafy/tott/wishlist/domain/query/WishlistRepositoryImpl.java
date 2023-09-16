package com.ssafy.tott.wishlist.domain.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tott.wishlist.vo.QWishlistVO;
import com.ssafy.tott.wishlist.vo.WishlistVO;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tott.housedetail.domain.QHouseDetail.houseDetail;
import static com.ssafy.tott.housegeo.domain.QHouseGeo.houseGeo;
import static com.ssafy.tott.member.domain.QMember.member;
import static com.ssafy.tott.region.domain.QRegion.region;
import static com.ssafy.tott.wishlist.domain.QWishlist.wishlist;

@Slf4j
public class WishlistRepositoryImpl implements WishlistRepositoryCustom {
    private final JPAQueryFactory query;

    public WishlistRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<WishlistVO> findByMemberId(int memberId) {
        return query.select(new QWishlistVO(
                                houseDetail.id,
                                region.districtName,
                                region.legalDongName,
                                houseGeo.buildingName,
                                houseGeo.latitude,
                                houseGeo.longitude,
                                houseDetail.area,
                                houseDetail.price,
                                houseGeo.constructionYear,
                                houseDetail.floor,
                                houseGeo.buildingType
                        )
                )
                .from(wishlist)
                .join(wishlist.member, member)
                .join(wishlist.houseDetail, houseDetail)
                .join(houseDetail.houseGeo, houseGeo)
                .join(houseGeo.region, region)
                .where(wishlist.member.id.eq(memberId))
                .fetch();
    }
}