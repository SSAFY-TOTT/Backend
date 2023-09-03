package com.ssafy.tott.api.shinhan.service.searchname.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.dto.response.header.ResponseDataHeader;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.body.SearchNameResponseDataBody;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ShinhanBankSearchNameResponse extends ShinhanBankAPIResponse {
    @JsonProperty
    private ResponseDataHeader dataHeader;

    @JsonProperty
    private SearchNameResponseDataBody dataBody;
}
