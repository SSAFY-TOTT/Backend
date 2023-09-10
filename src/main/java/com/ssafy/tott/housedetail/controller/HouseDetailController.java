package com.ssafy.tott.housedetail.controller;

import com.ssafy.tott.housedetail.data.dto.request.HouseDetailFilterRequest;
import com.ssafy.tott.housedetail.data.dto.response.HouseSearchResponse;
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

    @GetMapping("/search")
    public ResponseEntity<HouseSearchResponse> search(@RequestBody HouseDetailFilterRequest request) {
        HouseSearchResponse response = houseDetailService.search(request);
        return ResponseEntity.ok(response);
    }
}
