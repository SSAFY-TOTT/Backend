package com.ssafy.tott.wishlist.domain.query;

import com.ssafy.tott.wishlist.vo.WishlistVO;

import java.util.List;

public interface WishlistRepositoryCustom {
    List<WishlistVO> findByMemberId(int memberId);
}
