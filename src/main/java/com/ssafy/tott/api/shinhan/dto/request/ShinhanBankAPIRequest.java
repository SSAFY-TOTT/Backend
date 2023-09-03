package com.ssafy.tott.api.shinhan.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.DataBody;
import com.ssafy.tott.api.shinhan.dto.request.header.RequestDataHeader;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ShinhanBankAPIRequest {
    @JsonProperty
    private RequestDataHeader dataHeader;
    @JsonProperty
    private DataBody dataBody;

    private ShinhanBankAPIRequest(String key, DataBody dataBody) {
        this.dataHeader = RequestDataHeader.from(key);
        this.dataBody = dataBody;
    }

    public static ShinhanBankAPIRequest of(String key, DataBody dataBody) {
        return new ShinhanBankAPIRequest(key, dataBody);
    }
}
