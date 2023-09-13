package com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.body.ShinhanBankSearchCreditLineResponseDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchCreditLineResponse extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    ShinhanBankSearchCreditLineResponseDataBody responseDataBody;
}
