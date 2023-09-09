package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchAccountsRequestBody extends ShinhanBankDataBody {
    @JsonProperty("실명번호")
    private String encodedName;

    private ShinhanBankSearchAccountsRequestBody(String encodedName) {
        this.encodedName = encodedName;
    }

    public static ShinhanBankSearchAccountsRequestBody from(String encodedName) {
        return new ShinhanBankSearchAccountsRequestBody(encodedName);
    }
}
