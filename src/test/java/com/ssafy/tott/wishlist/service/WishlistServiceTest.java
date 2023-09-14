package com.ssafy.tott.wishlist.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.global.config.ServiceTest;
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
import com.ssafy.tott.wishlist.dto.response.WishlistCreateResponse;
import com.ssafy.tott.wishlist.dto.response.WishlistViewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WishlistServiceTest extends ServiceTest {

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private WishlistRepository wishlistRepository;
    private Member member;
    private HouseDetail houseDetail;

    @BeforeEach
    void setup() {
        member = saveMember(MemberFixture.SHINHAN);
        Region region = regionRepository.save(RegionFixture.REGION_ONE.toRegion());
        HouseGeo houseGeo = houseGeoRepository.save(HouseGeoFixture.GRAND_TOWER.toHouseGeo(region));
        houseDetail = houseDetailRepository.save(HouseDetailFixture.GRAND_TOWER_3.toHouseDetail(houseGeo));
    }

    @DisplayName("위시리스트를 저장한다.")
    @Test
    void saveTestSuccess() {
        /* Given */
        /* When */
        WishlistCreateResponse response = wishlistService.create(member.getId(), houseDetail.getId());

        /* Then */
        assertAll(
                () -> assertThat(response).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response).getMemberId()).isEqualTo(member.getId()),
                () -> assertThat(Objects.requireNonNull(response).getHouseId()).isEqualTo(houseDetail.getId())
        );
    }

    @DisplayName("위시리스트를 조회한다.")
    @Test
    void viewTestSuccess() {
        /* Given */
        wishlistService.create(member.getId(), houseDetail.getId());

        /* When */
        WishlistViewResponse response = wishlistService.view(member.getId());

        /* Then */
        assertThat(response.getWishlistVOList()).hasSize(1);
    }

    @DisplayName("위시리스트를 삭제한다.")
    @Test
    void removeTestSuccess() {
        /* Given */
        Wishlist savedWishlist = wishlistRepository.save(Wishlist.builder()
                .member(member)
                .houseDetail(houseDetail)
                .build());

        /* When */
        wishlistService.remove(savedWishlist.getId(), new AuthMember(member.getId()));

        /* Then */
        WishlistViewResponse response = wishlistService.view(member.getId());
        assertThat(response.getWishlistVOList()).isEmpty();
    }
}