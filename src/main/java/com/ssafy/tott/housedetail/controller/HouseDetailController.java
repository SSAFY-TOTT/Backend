package com.ssafy.tott.housedetail.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailStateResponse;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/house")
@RequiredArgsConstructor
@RestController
public class HouseDetailController {

    private final HouseDetailService houseDetailService;

    @PostMapping("/auth/recent")
    public ResponseEntity<HouseDetailRecentViewResponse> recentView(
            @Authenticated AuthMember authMember,
            @RequestBody HouseDetailRecentViewRequest request) {
        HouseDetailRecentViewResponse response = houseDetailService.findByRecentView(authMember, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/state")
    public ResponseEntity<HouseDetailStateResponse> state(
            @Authenticated AuthMember authMember,
            @RequestParam int houseDetailId) {
        HouseDetailStateResponse response = houseDetailService.searchState(authMember, houseDetailId);
        return ResponseEntity.ok(response);
    }
}
