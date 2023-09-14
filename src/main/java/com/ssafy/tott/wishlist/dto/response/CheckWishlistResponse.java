package com.ssafy.tott.wishlist.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckWishlistResponse {
    private boolean isWishlist;

    public static CheckWishlistResponse from(boolean isWishlist) {
        return new CheckWishlistResponse(isWishlist);
    }
}
