package com.ssafy.tott.api.kakao.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.APIResponse;
import com.ssafy.tott.api.kakao.data.vo.Documents;
import lombok.Data;

import java.util.List;

@Data
public class KakaoAPIResponse implements APIResponse {
    @JsonProperty
    private List<Documents> documents;
}
