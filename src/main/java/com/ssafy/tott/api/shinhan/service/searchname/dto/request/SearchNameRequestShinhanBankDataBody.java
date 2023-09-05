package com.ssafy.tott.api.shinhan.service.searchname.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchNameRequestShinhanBankDataBody extends ShinhanBankDataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;
    @JsonProperty("입금계좌번호")
    private String account;

    private SearchNameRequestShinhanBankDataBody(String bankCode, String account) {
        this.bankCode = bankCode;
        this.account = account;
    }

    public static SearchNameRequestShinhanBankDataBody of(String bankCode, String account) {
        return new SearchNameRequestShinhanBankDataBody(bankCode, account);
    }
}
