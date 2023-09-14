package com.ssafy.tott.wishlist.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WishlistCreateResponse {
    private int memberId;
    private int houseId;

    private WishlistCreateResponse(int memberId, int houseId) {
        this.memberId = memberId;
        this.houseId = houseId;
    }

    public static WishlistCreateResponse of(int memberId, int houseId) {
        return new WishlistCreateResponse(memberId, houseId);
    }
}
