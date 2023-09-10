package com.ssafy.tott.api.shinhan.service.searchname.dto.request;

import com.ssafy.tott.api.core.dto.request.APIJsonRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ShinhanBankSearchNameRequest implements APIJsonRequest {
    private String json;

    public static ShinhanBankSearchNameRequest toRequest(String json) {
        return new ShinhanBankSearchNameRequest(json);
    }
}
