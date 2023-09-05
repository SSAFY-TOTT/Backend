package com.ssafy.tott.global.fixture;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum MemberFixture {
    SHINHAN("shinhan1234@gmail.com", "Password1234!@", "12345678", BankCode.SHINHAN, "1234567890"),
    JEONGUK("workju1124@gmail.com", "Password123!@", "64817041", BankCode.SHINHAN, "110545895267");

    private String email;
    private String password;
    private String phoneNumber;
    private BankCode bankCode;
    private String accountNumber;

    MemberFixture(String email, String password, String phoneNumber, BankCode bankCode, String accountNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }

    public MemberSignupRequest toMemberSignupRequest() {
        return new MemberSignupRequest(email, password, phoneNumber, bankCode, accountNumber);
    }
}
