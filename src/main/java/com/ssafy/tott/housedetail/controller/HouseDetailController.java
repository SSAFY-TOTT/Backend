package com.ssafy.tott.housedetail.controller;

import com.ssafy.tott.housedetail.service.HouseDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/house")
@RequiredArgsConstructor
@RestController
public class HouseDetailController {

    private final HouseDetailService houseDetailService;
    /* TODO: 2023/09/12 최근 본 목록으로 조회 (HouseDetail ID) */

    /* TODO: 2023/09/12 GeoId로 조회 */
}
