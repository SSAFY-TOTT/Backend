package com.ssafy.tott.account.controller;

import com.ssafy.tott.account.data.dto.response.FindMoneyResponse;
import com.ssafy.tott.account.service.AccountService;
import com.ssafy.tott.auth.annotation.Authenticated;
import com.ssafy.tott.auth.vo.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.annotation.Nullable;

@RequiredArgsConstructor
@RequestMapping("/api/account")
@RestController
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/auth/filter")
    public ResponseEntity<FindMoneyResponse> findMoneyByCond(@Authenticated AuthMember authMember,
                                                             @RequestParam @Nullable Integer price) {
        FindMoneyResponse response = accountService.findMoneyByCond(authMember, price);
        return ResponseEntity.ok(response);
    }
}
