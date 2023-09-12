package com.ssafy.tott.wishlist.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.wishlist.dto.request.CreateWishlistRequest;
import com.ssafy.tott.wishlist.dto.request.ViewWishlistRequest;
import com.ssafy.tott.wishlist.dto.response.CreateWishlistResponse;
import com.ssafy.tott.wishlist.dto.response.ViewWishlistResponse;
import com.ssafy.tott.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wishlist")
@RequiredArgsConstructor
@RestController
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/create")
    public ResponseEntity<CreateWishlistResponse> create(@RequestBody CreateWishlistRequest request) {
        wishlistService.verifyLimit(request.getMemberId());
        CreateWishlistResponse response = wishlistService.create(request.getMemberId(), request.getHouseDetailId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/auth/remove")
    public ResponseEntity remove(
            @Authenticated AuthMember authMember,
            @RequestParam("wishlistId")int wishlistId) {
        wishlistService.remove(wishlistId, authMember);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/view")
    public ViewWishlistResponse view(@RequestBody ViewWishlistRequest request) {
        return wishlistService.view(request.getMemberId());
    }
}
