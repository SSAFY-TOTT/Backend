package com.ssafy.tott.api.shinhan.service.searchname.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;
import com.ssafy.tott.api.shinhan.service.searchname.dto.response.body.SearchNameResponseShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchNameResponse extends ShinhanBankAPIResponse {
    @JsonProperty("dataBody")
    private SearchNameResponseShinhanBankDataBody searchNameResponseShinhanBankDataBody;

    public String getOwnerName() {
        return searchNameResponseShinhanBankDataBody.getAccountOwnerName();
    }
}
