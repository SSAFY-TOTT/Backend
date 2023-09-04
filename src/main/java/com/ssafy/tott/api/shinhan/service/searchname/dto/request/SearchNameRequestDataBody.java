package com.ssafy.tott.api.shinhan.service.searchname.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.DataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchNameRequestDataBody extends DataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;
    @JsonProperty("입금계좌번호")
    private String account;

    private SearchNameRequestDataBody(String bankCode, String account) {
        this.bankCode = bankCode;
        this.account = account;
    }

    public static SearchNameRequestDataBody of(String bankCode, String account) {
        return new SearchNameRequestDataBody(bankCode, account);
    }
}
