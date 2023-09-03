package com.ssafy.tott.api;

import com.ssafy.tott.api.shinhan.ShinhanBankAPI;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final ShinhanBankAPI api;

    @GetMapping("/test")
    public ResponseEntity<ShinhanBankAPIResponse> fetchAPI() {
        return ResponseEntity.ok(api.getTransfer1API("088", "110222999999", "1234 SSAFY"));
    }
}
