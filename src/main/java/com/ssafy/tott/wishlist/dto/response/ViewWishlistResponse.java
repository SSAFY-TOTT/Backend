package com.ssafy.tott.wishlist.dto.response;

import com.ssafy.tott.wishlist.vo.WishlistVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ViewWishlistResponse {
    private List<WishlistVO> wishlistVOList;

    private ViewWishlistResponse(List<WishlistVO> wishlistVOList) {
        this.wishlistVOList = wishlistVOList;
    }

    public static ViewWishlistResponse from(List<WishlistVO> wishlistVOList) {
        return new ViewWishlistResponse(wishlistVOList);
    }
}
