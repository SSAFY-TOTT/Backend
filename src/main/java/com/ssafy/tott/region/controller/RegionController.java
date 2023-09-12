package com.ssafy.tott.region.controller;

import com.ssafy.tott.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/region")
@RestController
public class RegionController {
    private final RegionService regionService;
}
