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
}
