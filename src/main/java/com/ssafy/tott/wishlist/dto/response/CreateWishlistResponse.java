package com.ssafy.tott.wishlist.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreateWishlistResponse {
    private int memberId;
    private int houseId;

    private CreateWishlistResponse(int memberId, int houseId) {
        this.memberId = memberId;
        this.houseId = houseId;
    }

    public static CreateWishlistResponse of(int memberId, int houseId) {
        return new CreateWishlistResponse(memberId, houseId);
    }
}
