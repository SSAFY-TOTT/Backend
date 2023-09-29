package com.ssafy.tott.api.shinhan.service.transfer1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import com.ssafy.tott.api.shinhan.service.transfer1.dto.response.body.Transfer1ResponseShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShinhanBankTransfer1Response extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    private Transfer1ResponseShinhanBankDataBody transfer1ResponseShinhanBankDataBody;

    private ShinhanBankTransfer1Response(ResponseDataHeader dataHeader,
                                         Transfer1ResponseShinhanBankDataBody dataBody) {
        setDataHeader(dataHeader);
        this.transfer1ResponseShinhanBankDataBody = dataBody;
    }

    public static ShinhanBankTransfer1Response of(ResponseDataHeader dataHeader,
                                                  Transfer1ResponseShinhanBankDataBody dataBody) {
        return new ShinhanBankTransfer1Response(dataHeader, dataBody);
    }

    public Transfer1ResponseShinhanBankDataBody getDataBody() {
        return transfer1ResponseShinhanBankDataBody;
    }
}
