package com.ssafy.tott.wishlist.domain;

import com.ssafy.tott.global.config.RepositoryTest;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housedetail.fixture.HouseDetailFixture;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.housegeo.fixture.HouseGeoFixture;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.fixture.RegionFixture;
import com.ssafy.tott.wishlist.vo.WishlistVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WishlistRepositoryTest extends RepositoryTest {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    private HouseDetail houseDetail;
    private Member member;


    @BeforeEach
    void setUp() {
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        HouseGeo houseGeo = houseGeoRepository.save(HouseGeoFixture.SAM_DO.toHouseGeo(region));
        houseDetail = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(houseGeo));
        member = saveMember(MemberFixture.JEONGUK);
    }

    @DisplayName("memberId별 wishList찾기")
    @Test
    void findWishListByMemberId() {
        /* Given */
        wishlistRepository.save(Wishlist.builder()
                .member(member)
                .houseDetail(houseDetail)
                .build());

        /* When */
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(member.getId());

        /* Then */
        assertEquals(1, wishlistVOList.size());
    }

    @DisplayName("wishlist 삭제")
    @Nested
    class deleteWishList {
        @DisplayName("wishlsit의 memberid와 요청자의 member id가 같을 때")
        @Test
        void deleteWishListWhenSuccess() {
            /* Given */
            Wishlist wishlist = wishlistRepository.save(Wishlist.builder()
                    .member(member)
                    .houseDetail(houseDetail)
                    .build());

            /* When */
            Wishlist findWishList =
                    wishlistRepository.findByIdAndMemberId(wishlist.getId(), member.getId()).orElseThrow();
            findWishList.removeRelated();
            wishlistRepository.delete(findWishList);

            /* Then */
            List<Wishlist> afterWishList = wishlistRepository.findAll();
            assertEquals(0, afterWishList.size());
        }

        @DisplayName("wishlsit의 memberid와 요청자의 member id가 다를 때")
        @Test
        void deleteWishListWhenFail() {
            /* Given */
            int wishlistId = wishlistRepository.save(Wishlist.builder()
                    .member(member)
                    .houseDetail(houseDetail)
                    .build()).getId();

            /* When, Then */
            assertThat(wishlistRepository.findByIdAndMemberId(wishlistId, 200)).isEmpty();
        }
    }
}