package com.ssafy.tott.wishlist.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreateWishlistRequest {
    private int memberId;
    private int houseDetailId;

    public CreateWishlistRequest(int memberId, int houseDetailId) {
        this.memberId = memberId;
        this.houseDetailId = houseDetailId;
    }
}
