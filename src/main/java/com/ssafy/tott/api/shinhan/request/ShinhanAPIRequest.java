package com.ssafy.tott.api.shinhan.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.request.body.DataBody;
import com.ssafy.tott.api.shinhan.request.header.DataHeader;
import lombok.Data;

@Data
public class ShinhanAPIRequest {
    @JsonProperty
    private DataHeader dataHeader;
    @JsonProperty
    private DataBody dataBody;

    private ShinhanAPIRequest(String key, DataBody dataBody) {
        this.dataHeader = DataHeader.from(key);
        this.dataBody = dataBody;
    }

    public static ShinhanAPIRequest of(String key, DataBody dataBody) {
        return new ShinhanAPIRequest(key, dataBody);
    }
}
