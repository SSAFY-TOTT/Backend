package com.ssafy.tott.member.domain.embbeded;

import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {
  private static final String EMAIL_FORMAT = "^[a-z0-9._-]{8,16}+@[a-z]+[.]+[a-z]{2,3}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_FORMAT);

  @Column(name = "email", unique = true, nullable = false)
  private String value;

  private Email(String email) {
    value = email;
  }

  public static Email from(String email) {
    validateEmailFormat(email);
    return new Email(email);
  }

  private static void validateEmailFormat(String email) {
    if (!EMAIL_PATTERN.matcher(email).matches()) {
      /* TODO: 2023/09/02 추후 `Custom Exception`으로 수정 */
      throw new RuntimeException();
    }
  }
}
