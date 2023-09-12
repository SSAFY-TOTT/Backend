package com.ssafy.tott.wishlist.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ViewWishlistRequest {
    private int memberId;

    public ViewWishlistRequest(int memberId) {
        this.memberId = memberId;
    }
}
