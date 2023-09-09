package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchAccountsResponseDataBody {
    @JsonProperty("조회내역1")
    List<ShinhanBankSearchAccountsResponseAccount> accounts;
}
