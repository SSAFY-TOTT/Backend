package com.ssafy.tott.member.data.dto.responsse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberVerificationResponse {
    private int memberId;

    private MemberVerificationResponse(int memberId) {
        this.memberId = memberId;
    }

    public static MemberVerificationResponse from(int memberId) {
        return new MemberVerificationResponse(memberId);
    }
}
