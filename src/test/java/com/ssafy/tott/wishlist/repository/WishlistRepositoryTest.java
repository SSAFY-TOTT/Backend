package com.ssafy.tott.wishlist.repository;

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
import com.ssafy.tott.wishlist.domain.Wishlist;
import com.ssafy.tott.wishlist.domain.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WishlistRepositoryTest extends RepositoryTest {
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    private Member member;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        HouseGeo houseGeo = houseGeoRepository.save(HouseGeoFixture.JU_GONG4.toHouseGeo(region));
        houseDetail = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(houseGeo));
    }


    @DisplayName("existWishlist 테스트")
    @Nested
    class existWishlistTest{
        @DisplayName("존재할 때")
        @Test
        public void existWishlistTestWhenExisted() {
            /* Given */
            int memberId = member.getId();
            int houseDetailId = houseDetail.getId();
            wishlistRepository.save(Wishlist.builder().houseDetail(houseDetail).member(member).build());

            /* When */
            boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseDetailId(memberId, houseDetailId);

            /* Then */
            assertTrue(isWishlist);
        }

        @DisplayName("존재하지 않을 때")
        @Test
        public void existWishlistTestWhenNotExisted() {
            /* Given */
            int memberId = member.getId();
            int houseDetailId = houseDetail.getId()+1;
            wishlistRepository.save(Wishlist.builder().houseDetail(houseDetail).member(member).build());

            /* When */
            boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseDetailId(memberId, houseDetailId);

            /* Then */
            assertFalse(isWishlist);
        }
    }

}
