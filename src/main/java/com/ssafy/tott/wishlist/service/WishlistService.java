package com.ssafy.tott.wishlist.service;

import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.service.MemberService;
import com.ssafy.tott.wishlist.domain.Wishlist;
import com.ssafy.tott.wishlist.domain.WishlistRepository;
import com.ssafy.tott.wishlist.dto.response.WishlistCheckResponse;
import com.ssafy.tott.wishlist.dto.response.WishlistCreateResponse;
import com.ssafy.tott.wishlist.dto.response.WishlistViewResponse;
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

    /* TODO: 2023/09/13 해당 기능은 `WishlistService` 에서만 사용할 예정이기 때문에 `private`이 좋습니다. */
    public void verifyLimit(int memberID) {
        Member findMember = memberService.findById(memberID);
        if (findMember.getWishlists().size() >= 5)
            throw new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_FULL);
    }

    @Transactional
    public WishlistCreateResponse create(int memberID, int houseId) {
        Member findMember = memberService.findById(memberID);
        HouseDetail findHouseDetail = houseDetailService.findById(houseId);

        wishlistRepository.save(Wishlist.builder()
                .member(findMember)
                .houseDetail(findHouseDetail)
                .build()
        );
        return WishlistCreateResponse.of(memberID, houseId);
    }

    public void remove(int wishlistId, AuthMember authMember) {
        Wishlist wishlist = wishlistRepository
                .findByIdAndMemberId(wishlistId, authMember.getMemberId())
                .orElseThrow(
                        () -> new WishlistException(WishlistErrorCode.ERROR_CLIENT_WITH_WISHLIST_IS_NOT_EXISTED));
        wishlist.removeRelated();
        wishlistRepository.delete(wishlist);
    }

    public WishlistViewResponse view(int memberId) {
        /* TODO: 2023/09/13 builder -> from 으로 수정 */
        List<WishlistVO> wishlistVOList = wishlistRepository.findByMemberId(memberId).stream().map(wishlist -> WishlistVO.builder()
                .price(wishlist.getPrice())
                .area(wishlist.getArea())
                .buildingName(wishlist.getBuildingName())
                .districtName(wishlist.getDistrictName())
                .legalDongName(wishlist.getLegalDongName())
                .build()).collect(Collectors.toList());
        return WishlistViewResponse.from(wishlistVOList);
    }

    public WishlistCheckResponse check(int memberId, int houseDetailId) {
        boolean isWishlist = wishlistRepository.existsByMemberIdAndHouseDetailId(memberId, houseDetailId);
        return WishlistCheckResponse.from(isWishlist);
    }
}
