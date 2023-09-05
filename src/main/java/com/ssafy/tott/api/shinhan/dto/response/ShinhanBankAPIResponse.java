package com.ssafy.tott.api.shinhan.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.APIResponse;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ShinhanBankAPIResponse implements APIResponse {
    private static final String ERROR_CODE = "1";
    @JsonProperty("dataHeader")
    private ResponseDataHeader dataHeader;

    public boolean isFailed() {
        return dataHeader.getSuccessCode().equals(ERROR_CODE);
    }
}
