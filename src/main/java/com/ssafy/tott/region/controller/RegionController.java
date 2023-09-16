package com.ssafy.tott.region.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.region.data.dto.response.DistrictResponse;
import com.ssafy.tott.region.data.dto.response.LegalDongResponse;
import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/auth/find/legal")
    public ResponseEntity<?> searchLegalDong(@Authenticated AuthMember authMember,
                                             @RequestParam int districtCode) {
        LegalDongResponse response = regionService.findLegalDongAll(districtCode);
        return ResponseEntity.ok(response);
    }
}
