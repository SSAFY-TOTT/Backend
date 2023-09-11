package com.ssafy.tott.api.shinhan.service.transfer1.dto.request;

import com.ssafy.tott.api.core.dto.request.APIJsonRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ShinhanBankTransfer1Request implements APIJsonRequest {
    private String json;

    public static ShinhanBankTransfer1Request toRequest(String json) {
        return new ShinhanBankTransfer1Request(json);
    }
}
