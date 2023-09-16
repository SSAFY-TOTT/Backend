package com.ssafy.tott.wishlist.dto.response;

import com.ssafy.tott.wishlist.vo.WishlistVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class WishlistViewResponse {
    private List<WishlistVO> wishlistVOList;
    private int resultCount;

    public static WishlistViewResponse from(List<WishlistVO> list) {
        return WishlistViewResponse.builder()
                .wishlistVOList(list)
                .resultCount(list.size())
                .build();
    }
}
