package com.ssafy.tott.api.shinhan.service.searchamount.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchSavingAccountRequestShinhanBankDataBody extends ShinhanBankDataBody {
    @JsonProperty("계좌번호")
    private String account;

    private SearchSavingAccountRequestShinhanBankDataBody(String account) {
        this.account = account;
    }

    public static SearchSavingAccountRequestShinhanBankDataBody from(String account) {
        return new SearchSavingAccountRequestShinhanBankDataBody(account);
    }
}
