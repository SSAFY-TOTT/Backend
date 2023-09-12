package com.ssafy.tott.wishlist.domain;

import com.ssafy.tott.wishlist.domain.query.WishlistRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>, WishlistRepositoryCustom {
    Optional<Wishlist> findByIdAndMemberId(int wishlistId, int memberId);
}
