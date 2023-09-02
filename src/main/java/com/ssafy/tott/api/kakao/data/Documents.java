package com.ssafy.tott.api.kakao.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Documents {
    @JsonProperty("x")
    private String x;

    @JsonProperty("y")
    private String y;
}
