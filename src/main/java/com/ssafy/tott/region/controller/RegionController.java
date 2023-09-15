package com.ssafy.tott.region.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.region.domain.Region;
import com.ssafy.tott.region.domain.RegionRepository;
import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/region")
@RestController
public class RegionController {
    private final RegionService regionService;

    /* TODO: 2023/09/15 자치구 코드 */
    @GetMapping("/auth/find")
    public ResponseEntity<?> searchDistrict(@Authenticated AuthMember authMember) {
        return ResponseEntity.ok().build();
    }
    /* TODO: 2023/09/15 법정동 코드 */
}
