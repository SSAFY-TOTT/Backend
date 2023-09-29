package com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.request.ShinhanBankTransfer1RequestDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Transfer1ResponseShinhanBankDataBody extends ShinhanBankDataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String account;

    private Transfer1ResponseShinhanBankDataBody(String bankCode, String account) {
        this.bankCode = bankCode;
        this.account = account;
    }

    public static Transfer1ResponseShinhanBankDataBody from(ShinhanBankTransfer1RequestDataBody dataBody) {
        return new Transfer1ResponseShinhanBankDataBody(dataBody.getBankCode(), dataBody.getAccount());
    }
}
