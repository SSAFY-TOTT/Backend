package com.ssafy.tott.member.controller;

import com.ssafy.tott.member.data.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.data.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.data.dto.responsse.MemberSignupResponse;
import com.ssafy.tott.member.data.dto.responsse.MemberVerificationResponse;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberSignupResponse> signup(@RequestBody MemberSignupRequest request) {
        MemberSignupResponse response = memberService.signup(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verification")
    public ResponseEntity<MemberVerificationResponse> verification(
            @RequestBody MemberVerificationRequest request) {
        MemberVerificationResponse response = memberService.verification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
