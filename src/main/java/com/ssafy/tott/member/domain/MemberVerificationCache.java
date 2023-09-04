package com.ssafy.tott.member.domain;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.account.domain.embbeded.AccountNumber;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "member_verification_cache", timeToLive = 60 * 3)
public class MemberVerificationCache {
    @Id
    private AccountNumber account;
    @Enumerated(EnumType.STRING)
    private BankCode bankCode;
    private Email email;
    private Password password;
    private PhoneNumber phoneNumber;
    private String memo;

    @Builder
    public MemberVerificationCache(AccountNumber account, BankCode bankCode, Email email, Password password,
                                   PhoneNumber phoneNumber, String memo) {
        this.account = account;
        this.bankCode = bankCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
    }
}
