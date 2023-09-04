package com.ssafy.tott.member.controller;

import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import com.ssafy.tott.member.dto.responsse.MemberSignupResponse;
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
        MemberSignupResponse response = memberService.save(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/proof")
    public ResponseEntity<?> proof() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
