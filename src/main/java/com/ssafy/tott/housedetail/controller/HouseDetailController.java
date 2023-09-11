package com.ssafy.tott.housedetail.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseDetailRecentViewResponse;
import com.ssafy.tott.housedetail.service.HouseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/house")
@RequiredArgsConstructor
@RestController
public class HouseDetailController {

    private final HouseDetailService houseDetailService;

    /* TODO: 2023/09/12 최근 본 목록으로 조회 (HouseDetail ID) */
    @GetMapping("/auth/recent")
    public ResponseEntity<HouseDetailRecentViewResponse> recentView(
            @Authenticated AuthMember authMember,
            @RequestBody HouseDetailRecentViewRequest request) {

        return ResponseEntity.ok().build();
    }
    /* TODO: 2023/09/12 GeoId로 조회 */
}