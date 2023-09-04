package com.ssafy.tott.api.shinhan.service.searchname.dto.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.DataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchNameResponseDataBody extends DataBody {
    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String account;

    @JsonProperty("입금계좌성명")
    private String accountOwnerName;
}
