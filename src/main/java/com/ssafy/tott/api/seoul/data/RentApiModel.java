package com.ssafy.tott.api.seoul.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RentApiModel {
    private final RentData tbLnOpendataRentV;

    @JsonCreator
    public RentApiModel(@JsonProperty("tbLnOpendataRentV") RentData tbLnOpendataRentV) {
        this.tbLnOpendataRentV = tbLnOpendataRentV;
    }
}
