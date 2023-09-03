package com.ssafy.tott.api.shinhan.api.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.request.body.DataBody;
import lombok.Data;

@Data
public class Transfer1DataBody implements DataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;
    @JsonProperty("입금계좌번호")
    private String account;
    @JsonProperty("입금통장메모")
    private String memo;

    private Transfer1DataBody(String bankCode, String account, String memo) {
        this.bankCode = bankCode;
        this.account = account;
        this.memo = memo;
    }

    public static Transfer1DataBody of(String bankCode, String account, String memo) {
        return new Transfer1DataBody(bankCode, account, memo);
    }
}
