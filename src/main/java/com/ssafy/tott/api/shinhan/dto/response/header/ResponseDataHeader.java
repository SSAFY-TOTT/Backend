package com.ssafy.tott.api.shinhan.dto.response.header;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ResponseDataHeader {
  @JsonProperty("successCode")
  private String successCode;

  @JsonProperty("resultCode")
  private String resultCode;

  @JsonProperty("resultMessage")
  private String resultMessage;
}
