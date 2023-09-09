package com.ssafy.tott.api.shinhan.dto.request.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class RequestDataHeader {
    @JsonProperty
    private String apikey;

    private RequestDataHeader(String apiKey) {
        this.apikey = apiKey;
    }

    public static RequestDataHeader from(String apiKey) {
        return new RequestDataHeader(apiKey);
    }
}
