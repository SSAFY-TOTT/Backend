package com.ssafy.tott.member.dto.request;

import com.ssafy.tott.account.domain.BankCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberSignupRequest {
    private String email;
    private String password;
    private String phoneNumber;
    private BankCode bankCode;
    private String accountNumber;
}
