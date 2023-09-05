package com.ssafy.tott.global.fixture;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.member.domain.Member;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import com.ssafy.tott.member.dto.request.MemberSignupRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum MemberFixture {
    SHINHAN("김신한", "shinhan1234@gmail.com", "Password1234!@", "12345678", BankCode.SHINHAN, "1234567890"),
    JEONGUK("김정욱", "workju1124@gmail.com", "Password123!@", "64817041", BankCode.SHINHAN, "110545895267");

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private BankCode bankCode;
    private String accountNumber;

    MemberFixture(String name, String email, String password, String phoneNumber, BankCode bankCode,
                  String accountNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }

    public MemberSignupRequest toMemberSignupRequest() {
        return new MemberSignupRequest(email, password, phoneNumber, bankCode, accountNumber);
    }

    public Member toMember() {
        return Member.builder().name(name)
                .email(Email.from(email))
                .password(Password.of(password, new BCryptPasswordEncoder()))
                .creditLine(0L)
                .phoneNumber(PhoneNumber.from(phoneNumber))
                .build();
    }
}
