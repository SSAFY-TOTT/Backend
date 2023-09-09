package com.ssafy.tott.api.kakao.data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.core.APIResponse;
import com.ssafy.tott.api.kakao.data.vo.Documents;
import java.util.List;
import lombok.Data;

@Data
public class KakaoAPIResponse implements APIResponse {
  @JsonProperty private List<Documents> documents;
}
