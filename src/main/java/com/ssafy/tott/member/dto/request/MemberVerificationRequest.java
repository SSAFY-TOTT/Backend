package com.ssafy.tott.member.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberVerificationRequest {
    private String accountNumber;
    private String memo;
}
