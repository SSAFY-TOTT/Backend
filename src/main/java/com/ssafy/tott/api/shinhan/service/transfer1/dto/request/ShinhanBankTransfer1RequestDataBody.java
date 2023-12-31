package com.ssafy.tott.api.shinhan.service.transfer1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.account.domain.BankCode;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class ShinhanBankTransfer1RequestDataBody extends ShinhanBankDataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String account;

    @JsonProperty("입금통장메모")
    private String memo;

    private ShinhanBankTransfer1RequestDataBody(String bankCode, String account, String memo) {
        this.bankCode = bankCode;
        this.account = account;
        this.memo = memo;
    }

    public static ShinhanBankTransfer1RequestDataBody of(
            BankCode bankCode, String account, String memo) {
        return new ShinhanBankTransfer1RequestDataBody(bankCode.getCode(), account, memo);
    }
}
