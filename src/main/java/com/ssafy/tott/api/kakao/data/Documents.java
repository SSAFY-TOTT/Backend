package com.ssafy.tott.api.kakao.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class Documents {
    @JsonProperty
    private String x;

    @JsonProperty
    private String y;
}
