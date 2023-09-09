package com.ssafy.tott.auth.domain;

import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "auth_token", timeToLive = 60 * 60 * 24 * 7)
public class AuthToken {
  @Id private int id;
  private String value;

  @Builder
  public AuthToken(int id, String value) {
    this.id = id;
    this.value = value;
  }
}
