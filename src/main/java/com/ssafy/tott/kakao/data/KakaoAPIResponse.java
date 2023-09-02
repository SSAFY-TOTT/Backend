package com.ssafy.tott.kakao.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoAPIResponse {
    @JsonProperty
    private List<Documents> documents;
}
