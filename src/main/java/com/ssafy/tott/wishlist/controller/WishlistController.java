package com.ssafy.tott.wishlist.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.wishlist.dto.response.CheckWishlistResponse;
import com.ssafy.tott.wishlist.dto.response.CreateWishlistResponse;
import com.ssafy.tott.wishlist.dto.response.ViewWishlistResponse;
import com.ssafy.tott.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/auth/create")
    public ResponseEntity<CreateWishlistResponse> create(
            @Authenticated AuthMember authMember,
            @RequestParam("houseDetailId")int houseDetailId) {
        wishlistService.verifyLimit(authMember.getMemberId());
        CreateWishlistResponse response = wishlistService.create(authMember.getMemberId(), houseDetailId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/auth/remove")
    public ResponseEntity remove(
            @Authenticated AuthMember authMember,
            @RequestParam("wishlistId")int wishlistId) {
        wishlistService.remove(wishlistId, authMember);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/view")
    public ResponseEntity<ViewWishlistResponse> view(@Authenticated AuthMember authMember) {
        ViewWishlistResponse response = wishlistService.view(authMember.getMemberId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/check")
    public ResponseEntity<CheckWishlistResponse> check(@Authenticated AuthMember authMember, @RequestParam("houseDetailId") int houseDetailId){
        CheckWishlistResponse response = wishlistService.check(authMember.getMemberId(), houseDetailId);
        return ResponseEntity.ok(response);
    }
}
