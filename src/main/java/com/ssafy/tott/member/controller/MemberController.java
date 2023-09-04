package com.ssafy.tott.member.controller;

import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.request.MemberVerificationRequest;
import com.ssafy.tott.member.dto.responsse.MemberSignupResponse;
import com.ssafy.tott.member.dto.responsse.MemberVerificationResponse;
import com.ssafy.tott.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<MemberVerificationResponse> verification(@RequestBody MemberVerificationRequest request) {
        MemberVerificationResponse response = memberService.verification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
