package com.ssafy.tott.api.shinhan.service.searchamount.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;

public class SearchSavingAccountResponseShinhanBankDataBody extends ShinhanBankDataBody {
    @JsonProperty("계좌번호")
    private String accountNumber;

    @JsonProperty("계좌잔액금액")
    private String amount;
}
