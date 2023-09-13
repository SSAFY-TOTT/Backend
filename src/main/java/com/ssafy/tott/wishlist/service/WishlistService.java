package com.ssafy.tott.wishlist.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import com.ssafy.tott.wishlist.domain.Wishlist;
import com.ssafy.tott.wishlist.domain.WishlistRepository;
import com.ssafy.tott.wishlist.dto.response.CreateWishlistResponse;
import com.ssafy.tott.wishlist.dto.response.ViewWishlistResponse;
import com.ssafy.tott.wishlist.exception.WishlistErrorCode;
import com.ssafy.tott.wishlist.exception.WishlistException;
import com.ssafy.tott.wishlist.vo.WishlistVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MemberService memberService;
    private final HouseDetailService houseDetailService;

    public void verifyLimit(int memberID) {
        Member findMember = memberService.findById(memberID);
        if (findMember.getWishlists().size() >= 5)
            throw new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_FULL);
    }

    public CreateWishlistResponse create(int memberID, int houseId) {
        Member findMember = memberService.findById(memberID);
        HouseDetail findHouseDetail = houseDetailService.findById(houseId);

        wishlistRepository.save(Wishlist.builder()
                .member(findMember)
                .houseDetail(findHouseDetail)
                .build()
        );
        return CreateWishlistResponse.of(memberID, houseId);
    }

    public void remove(int wishlistId, AuthMember authMember) {
        Wishlist wishlist = wishlistRepository
                .findByIdAndMemberId(wishlistId, authMember.getMemberId())
                .orElseThrow(
                        () -> new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_NOT_EXISTED));
        wishlist.removeRelated();
        wishlistRepository.delete(wishlist);
    }

    public ViewWishlistResponse view(int memberId) {
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(memberId).stream().map(wishlist -> WishlistVO.builder()
                .price(wishlist.getPrice())
                .area(wishlist.getArea())
                .buildingName(wishlist.getBuildingName())
                .districtName(wishlist.getDistrictName())
                .legalDongName(wishlist.getLegalDongName())
                .build()).collect(Collectors.toList());
        return ViewWishlistResponse.from(wishlistVOList);
    }
}