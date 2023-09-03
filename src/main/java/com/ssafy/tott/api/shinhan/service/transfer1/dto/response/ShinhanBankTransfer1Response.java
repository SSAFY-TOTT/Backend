package com.ssafy.tott.api.shinhan.service.transfer1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseDataBody;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ShinhanBankTransfer1Response extends ShinhanBankAPIResponse {
    @JsonProperty
    private ResponseDataHeader dataHeader;
    @JsonProperty
    private Transfer1ResponseDataBody dataBody;
}
