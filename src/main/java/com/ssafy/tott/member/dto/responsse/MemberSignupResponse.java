package com.ssafy.tott.member.dto.responsse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberSignupResponse {
    private String accountNumber;
    private String memo;

    private MemberSignupResponse(String accountNumber, String memo) {
        this.accountNumber = accountNumber;
        this.memo = memo;
    }

    public static MemberSignupResponse of(String accountNumber, String memo) {
        return new MemberSignupResponse(accountNumber, memo);
    }
}
