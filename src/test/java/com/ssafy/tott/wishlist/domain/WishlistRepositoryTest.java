package com.ssafy.tott.wishlist.domain;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import com.ssafy.tott.global.fixture.MemberFixture;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.domain.HouseDetailRepository;
import com.ssafy.tott.housegeo.domain.BuildingType;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import com.ssafy.tott.housegeo.domain.HouseGeoRepository;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.MemberRepository;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.wishlist.vo.WishlistVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class WishlistRepositoryTest {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    @Autowired
    private HouseGeoRepository houseGeoRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private MemberRepository memberRepository;

    private final RentRow row =
            new RentRow(
                    "2023",
                    "11380",
                    "은평구",
                    "10300",
                    "불광동",
                    "1",
                    "대지",
                    "0105",
                    "0076",
                    3.0,
                    "20230901",
                    "전세",
                    57.76,
                    "23000",
                    "0",
                    "105-76",
                    "2018",
                    "연립다세대",
                    "",
                    "신규",
                    "",
                    "0",
                    "");

    private Region region;
    private HouseGeo houseGeo;
    private HouseDetail houseDetail;
    private Member member;


    @BeforeEach
    void setUp() {
        region =
                regionRepository.save(
                        Region.builder()
                                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                                .legalDongName(row.getBjdongNm())
                                .districtCode(Integer.parseInt(row.getSggCd()))
                                .districtName(row.getSggNm())
                                .build());
        houseGeo =
                houseGeoRepository.save(
                        HouseGeo.builder()
                                .mainNumber(Integer.parseInt(row.getBobn()))
                                .subNumber(Integer.parseInt(row.getBubn()))
                                .longitude(0)
                                .latitude(0)
                                .buildingName(row.getBldgNm())
                                .buildingType(BuildingType.단독다가구)
                                .region(region)
                                .build());
        houseDetail =
                houseDetailRepository.save(
                        HouseDetail.builder()
                                .houseGeo(houseGeo)
                                .floor(row.getFlrNo())
                                .price(Integer.parseInt(row.getRentGtn()))
                                .area(row.getRentArea())
                                .build());

        member = memberRepository.save(MemberFixture.JEONGUK.toMember());
    }

    @DisplayName("memberId별 wishList찾기")
    @Test
    void findWishListByMemberId() {
        /* Given */
        wishlistRepository.save(Wishlist.builder().member(member).houseDetail(houseDetail).build());

        /* When */
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(1);

        /* Then */
        assertEquals(1,wishlistVOList.size());
    }

    @DisplayName("wishlist 삭제")
    @Nested
    class deleteWishList{
        @DisplayName("wishlsit의 memberid와 요청자의 member id가 같을 때")
        @Test
        void deleteWishListWhenSuccess(){
            /* Given */
            Wishlist wishlist = wishlistRepository.save(Wishlist.builder().member(member).houseDetail(houseDetail).build());
            List<Wishlist> originWishList = wishlistRepository.findAll();
            assertEquals(1,originWishList.size());
            /* When */
            Wishlist findWishList = wishlistRepository.findByIdAndMemberId(wishlist.getId(), member.getId()).orElseThrow();
            findWishList.removeRelated();
            wishlistRepository.delete(findWishList);

            /* Then */
            List<Wishlist> afterWishList = wishlistRepository.findAll();
            assertEquals(0,afterWishList.size());
        }

        @DisplayName("wishlsit의 memberid와 요청자의 member id가 다를 때")
        @Test
        void deleteWishListWhenFail(){
            /* Given */
            Wishlist wishlist = wishlistRepository.save(Wishlist.builder().member(member).houseDetail(houseDetail).build());
            List<Wishlist> originWishList = wishlistRepository.findAll();
            assertEquals(1,originWishList.size());
            /* When */
            assertThrows(NoSuchElementException.class, () ->{
                wishlistRepository.findByIdAndMemberId(wishlist.getId(), 200).orElseThrow();
            });
        }
    }

}