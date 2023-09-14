package com.ssafy.tott.wishlist.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WishlistCheckResponse {
    private boolean isWishlist;

    public static WishlistCheckResponse from(boolean isWishlist) {
        return new WishlistCheckResponse(isWishlist);
    }
}
