package com.ssafy.tott.api;

import com.ssafy.tott.api.shinhan.api.ShinhanBankTransfer1API;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final ShinhanBankTransfer1API transfer1;

    @GetMapping("/test")
    public String fetch() {
        return transfer1.fetchAPI();
    }
}
