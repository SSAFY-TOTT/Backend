package com.ssafy.tott.api.seoulopendata.data.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.core.dto.APIResponse;
import com.ssafy.tott.api.seoulopendata.data.vo.RentData;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RentAPIResponse implements APIResponse {
    private final RentData tbLnOpendataRentV;

    @JsonCreator
    public RentAPIResponse(@JsonProperty("tbLnOpendataRentV") RentData tbLnOpendataRentV) {
        this.tbLnOpendataRentV = tbLnOpendataRentV;
    }
}
