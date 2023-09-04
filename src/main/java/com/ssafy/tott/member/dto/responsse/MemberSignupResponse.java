package com.ssafy.tott.member.dto.responsse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberSignupResponse {
    private String memo;

    private MemberSignupResponse(String memo) {
        this.memo = memo;
    }

    public static MemberSignupResponse from(String memo) {
        return new MemberSignupResponse(memo);
    }
}
