package com.ssafy.tott.api.shinhan.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import com.ssafy.tott.api.shinhan.dto.request.header.RequestDataHeader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankAPIRequest {
    @JsonProperty("dataHeader")
    private RequestDataHeader dataHeader;
    @JsonProperty("dataBody")
    private ShinhanBankDataBody shinhanBankDataBody;

    private ShinhanBankAPIRequest(String key, ShinhanBankDataBody shinhanBankDataBody) {
        this.dataHeader = RequestDataHeader.from(key);
        this.shinhanBankDataBody = shinhanBankDataBody;
    }

    public static ShinhanBankAPIRequest of(String key, ShinhanBankDataBody shinhanBankDataBody) {
        return new ShinhanBankAPIRequest(key, shinhanBankDataBody);
    }
}
