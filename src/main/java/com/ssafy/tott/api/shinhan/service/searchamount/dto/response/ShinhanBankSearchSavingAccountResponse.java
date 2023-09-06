package com.ssafy.tott.api.shinhan.service.searchamount.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchamount.dto.response.body.SearchSavingAccountResponseShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchSavingAccountResponse extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    SearchSavingAccountResponseShinhanBankDataBody searchSavingAccountResponseShinhanBankDataBody;
}
