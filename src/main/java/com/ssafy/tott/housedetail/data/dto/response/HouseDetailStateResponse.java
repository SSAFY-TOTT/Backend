package com.ssafy.tott.housedetail.data.dto.response;

import com.ssafy.tott.housedetail.data.vo.HouseDetailStateVO;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailStateResponse {
    private boolean isWishlist;
    private HouseDetailStateVO houseDetailStateVO;

    public static HouseDetailStateResponse of(boolean isWishlist, long budget, long creditLine) {
        return HouseDetailStateResponse.builder()
                .isWishlist(isWishlist)
                .houseDetailStateVO(HouseDetailStateVO.of(budget, creditLine))
                .build();
    }
}
