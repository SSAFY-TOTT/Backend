package com.ssafy.tott.api.shinhan.dto.response.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ResponseDataHeader {
    @JsonProperty
    private String successCode;

    @JsonProperty
    private String resultCode;

    @JsonProperty
    private String resultMessage;
}
