package com.ssafy.tott.member.domain;

import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "member_verification_cache", timeToLive = 60 * 3)
public class MemberVerificationCache {
    @Id
    private String account;
    private int bankCode;
    private Email email;
    private Password password;
    private PhoneNumber phoneNumber;

    @Builder
    public MemberVerificationCache(String account, int bankCode, Email email, Password password, PhoneNumber phoneNumber) {
        this.account = account;
        this.bankCode = bankCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
