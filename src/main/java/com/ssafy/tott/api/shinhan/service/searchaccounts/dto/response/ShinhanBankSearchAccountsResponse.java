package com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body.ShinhanBankSearchAccountsResponseAccount;
import com.ssafy.tott.api.shinhan.service.searchaccounts.dto.response.body.ShinhanBankSearchAccountsResponseDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchAccountsResponse extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    ShinhanBankSearchAccountsResponseDataBody shinhanBankSearchAccountsResponseDataBody;

    /* API 데이터 가공 */
    public void initAccounts() {
        shinhanBankSearchAccountsResponseDataBody.getAccounts()
                .forEach(ShinhanBankSearchAccountsResponseAccount::initAccount);
    }
}
