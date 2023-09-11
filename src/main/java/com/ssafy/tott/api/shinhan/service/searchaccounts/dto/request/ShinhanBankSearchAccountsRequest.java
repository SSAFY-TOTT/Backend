package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.request;

import com.ssafy.tott.api.core.dto.request.APIJsonRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ShinhanBankSearchAccountsRequest implements APIJsonRequest {
    private String json;

    public static ShinhanBankSearchAccountsRequest toRequest(String json) {
        return new ShinhanBankSearchAccountsRequest(json);
    }
}
