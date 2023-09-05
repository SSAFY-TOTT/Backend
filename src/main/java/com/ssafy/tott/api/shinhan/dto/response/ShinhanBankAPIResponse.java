package com.ssafy.tott.api.shinhan.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.APIResponse;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import lombok.Getter;

@Getter
public abstract class ShinhanBankAPIResponse implements APIResponse {
    private static final String ERROR_CODE = "1";
    @JsonProperty
    private ResponseDataHeader dataHeader;

    public boolean isFailed() {
        return dataHeader.getSuccessCode().equals(ERROR_CODE);
    }
}
