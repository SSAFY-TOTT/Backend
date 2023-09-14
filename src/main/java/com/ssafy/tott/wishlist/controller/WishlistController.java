package com.ssafy.tott.wishlist.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.wishlist.dto.response.WishlistCheckResponse;
import com.ssafy.tott.wishlist.dto.response.WishlistCreateResponse;
import com.ssafy.tott.wishlist.dto.response.WishlistViewResponse;
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
    public ResponseEntity<WishlistCreateResponse> create(
            @Authenticated AuthMember authMember,
            @RequestParam("houseDetailId") int houseDetailId) {
        wishlistService.verifyLimit(authMember.getMemberId());
        WishlistCreateResponse response = wishlistService.create(authMember.getMemberId(), houseDetailId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/auth/remove")
    public ResponseEntity<Void> remove(
            @Authenticated AuthMember authMember,
            @RequestParam("wishlistId") int wishlistId) {
        wishlistService.remove(wishlistId, authMember);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/view")
    public ResponseEntity<WishlistViewResponse> view(@Authenticated AuthMember authMember) {
        WishlistViewResponse response = wishlistService.view(authMember.getMemberId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/check")
    public ResponseEntity<WishlistCheckResponse> check(@Authenticated AuthMember authMember, @RequestParam("houseDetailId") int houseDetailId){
        WishlistCheckResponse response = wishlistService.check(authMember.getMemberId(), houseDetailId);
        return ResponseEntity.ok(response);
    }
}
