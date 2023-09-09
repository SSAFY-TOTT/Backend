package com.ssafy.tott.member.domain;

import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.member.domain.embbeded.Email;
import com.ssafy.tott.member.domain.embbeded.Password;
import com.ssafy.tott.member.domain.embbeded.PhoneNumber;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "member_verification_cache", timeToLive = 60 * 3)
public class MemberVerificationCache {
  @Id private String id;

  @Enumerated(EnumType.STRING)
  private BankCode bankCode;

  @Embedded private Email email;
  @Embedded private Password password;
  @Embedded private PhoneNumber phoneNumber;
  private String memo;

  @Builder
  public MemberVerificationCache(
      String account,
      BankCode bankCode,
      Email email,
      Password password,
      PhoneNumber phoneNumber,
      String memo) {
    this.id = account;
    this.bankCode = bankCode;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.memo = memo;
  }

  public String getAccountNumber() {
    return id;
  }
}
