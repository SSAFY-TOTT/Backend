package com.ssafy.tott.api.shinhan.service.searchcreditline.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchCreditLineResponseDataBody {
    @JsonProperty("예상대출한도")
    private String creditLine;
}
