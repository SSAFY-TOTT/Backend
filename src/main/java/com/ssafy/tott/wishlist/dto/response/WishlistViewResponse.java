package com.ssafy.tott.wishlist.dto.response;

import com.ssafy.tott.wishlist.vo.WishlistVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WishlistViewResponse {
    private List<WishlistVO> wishlistVOList;

    private WishlistViewResponse(List<WishlistVO> wishlistVOList) {
        this.wishlistVOList = wishlistVOList;
    }

    public static WishlistViewResponse from(List<WishlistVO> wishlistVOList) {
        return new WishlistViewResponse(wishlistVOList);
    }
}
