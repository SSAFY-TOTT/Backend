package com.ssafy.tott.api.shinhan.service.transfer1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShinhanBankTransfer1Response extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    private Transfer1ResponseShinhanBankDataBody transfer1ResponseShinhanBankDataBody;

    public Transfer1ResponseShinhanBankDataBody getDataBody() {
        return transfer1ResponseShinhanBankDataBody;
    }
}
