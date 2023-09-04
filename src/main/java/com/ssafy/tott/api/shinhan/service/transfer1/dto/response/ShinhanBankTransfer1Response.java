package com.ssafy.tott.api.shinhan.service.transfer1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankTransfer1Response extends ShinhanBankAPIResponse {
    @JsonProperty
    private Transfer1ResponseShinhanBankDataBody dataBody;
}
