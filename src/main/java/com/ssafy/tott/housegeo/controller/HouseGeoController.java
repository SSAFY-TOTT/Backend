package com.ssafy.tott.housegeo.controller;

import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import com.ssafy.tott.housegeo.data.dto.request.GeoSearchRequest;
import com.ssafy.tott.housegeo.data.dto.response.HouseGeoSearchResponse;
import com.ssafy.tott.housegeo.service.HouseGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/geo")
@RestController
public class HouseGeoController {
    private final HouseGeoService houseGeoService;

    @GetMapping("/auth/search")
    public ResponseEntity<HouseGeoSearchResponse> search(
            @Authenticated AuthMember authMember,
            @RequestBody GeoSearchRequest request) {
        HouseGeoSearchResponse response = houseGeoService.findByFilter(request);
        return ResponseEntity.ok(response);
    }
}
