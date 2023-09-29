package com.ssafy.tott.api.shinhan.service.searchname.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchNameResponseDataBody extends ShinhanBankDataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String account;

    @JsonProperty("입금계좌성명")
    private String accountOwnerName;

    private ShinhanBankSearchNameResponseDataBody(String bankCode, String account, String accountOwnerName) {
        this.bankCode = bankCode;
        this.account = account;
        this.accountOwnerName = accountOwnerName;
    }

    public static ShinhanBankSearchNameResponseDataBody of(String bankCode, String account, String accountOwnerName) {
        return new ShinhanBankSearchNameResponseDataBody(bankCode, account, accountOwnerName);
    }
}
