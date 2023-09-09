package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchAccountsResponseAccount {
  @JsonProperty("구분")
  private String accountType;

  @JsonProperty("계좌번호")
  private String accountNumber;

  @JsonProperty("잔액(통화별)")
  private String amount;
}
