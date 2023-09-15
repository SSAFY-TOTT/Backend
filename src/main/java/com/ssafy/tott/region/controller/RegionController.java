package com.ssafy.tott.region.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.region.data.dto.response.DistrictResponse;
import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/region")
@RestController
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/auth/find/district")
    public ResponseEntity<DistrictResponse> searchDistrict(@Authenticated AuthMember authMember) {
        DistrictResponse response = regionService.findDistrictAll();
        return ResponseEntity.ok(response);
    }

    /* TODO: 2023/09/15 법정동 코드 */
}
