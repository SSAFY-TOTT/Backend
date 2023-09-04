package com.ssafy.tott.member.dto.responsse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MemberSignupResponse {
    private String accountNumber;
    private String bankCode;

    private MemberSignupResponse(String accountNumber, String bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    public static MemberSignupResponse of(String accountNumber, String bankCode) {
        return new MemberSignupResponse(accountNumber, bankCode);
    }
}
