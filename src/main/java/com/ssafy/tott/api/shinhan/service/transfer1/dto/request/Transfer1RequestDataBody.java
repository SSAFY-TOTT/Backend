package com.ssafy.tott.api.shinhan.service.transfer1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.DataBody;
import lombok.Data;

@Data
public class Transfer1RequestDataBody implements DataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;
    @JsonProperty("입금계좌번호")
    private String account;
    @JsonProperty("입금통장메모")
    private String memo;

    private Transfer1RequestDataBody(String bankCode, String account, String memo) {
        this.bankCode = bankCode;
        this.account = account;
        this.memo = memo;
    }

    public static Transfer1RequestDataBody of(String bankCode, String account, String memo) {
        return new Transfer1RequestDataBody(bankCode, account, memo);
    }
}
