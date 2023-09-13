package com.ssafy.tott.account.fixture;

import com.ssafy.tott.account.domain.Account;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum AccountFixture {
    ACCOUNT_ONE(AccountNumber.from("1234567890"), BankCode.SHINHAN, 100_000_000L),
    ACCOUNT_TWO(AccountNumber.from("1234567891"), BankCode.SHINHAN, 200_000_000L),
    ;

    private AccountNumber accountNumber;
    private BankCode bankCode;
    private Long amount;

    public Account toAccount(Member member) {
        return new Account(accountNumber, bankCode, amount, member);
    }
}
