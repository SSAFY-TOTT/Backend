package com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request;

import com.ssafy.tott.api.core.dto.request.APIJsonRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ShinhanBankSearchCreditLineRequest implements APIJsonRequest {
    private String json;

    public static ShinhanBankSearchCreditLineRequest toRequest(String json) {
        return new ShinhanBankSearchCreditLineRequest(json);
    }
}
