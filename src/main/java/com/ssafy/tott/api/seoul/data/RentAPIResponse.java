package com.ssafy.tott.api.seoul.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.APIResponse;
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
