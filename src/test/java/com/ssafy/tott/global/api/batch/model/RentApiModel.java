package com.ssafy.tott.global.api.batch.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
public class RentApiModel {
    private final RentData tbLnOpendataRentV;

    @JsonCreator
    public RentApiModel(@JsonProperty("tbLnOpendataRentV") RentData tbLnOpendataRentV) {
        this.tbLnOpendataRentV = tbLnOpendataRentV;
    }
}
