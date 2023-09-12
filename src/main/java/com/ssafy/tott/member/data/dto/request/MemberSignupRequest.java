package com.ssafy.tott.member.data.dto.request;

import com.ssafy.tott.account.domain.BankCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MemberSignupRequest {
    private String email;
    private String password;
    private String validPassword;
    private String phoneNumber;
    private BankCode bankCode;
    private String accountNumber;
}
