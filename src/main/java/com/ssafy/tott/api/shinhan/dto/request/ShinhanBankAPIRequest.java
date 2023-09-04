package com.ssafy.tott.api.shinhan.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import com.ssafy.tott.api.shinhan.dto.request.header.RequestDataHeader;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ShinhanBankAPIRequest {
    @JsonProperty
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
