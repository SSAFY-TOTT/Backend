package com.ssafy.tott.api.shinhan.request.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataHeader {
    @JsonProperty
    private String apikey;

    private DataHeader(String apiKey) {
        this.apikey = apiKey;
    }

    public static DataHeader from(String apiKey) {
        return new DataHeader(apiKey);
    }
}
