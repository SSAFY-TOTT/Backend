package com.ssafy.tott.global.api.batch.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class RentData {
    private final int listTotalCount;
    private final List<RentRow> row;

    @JsonCreator
    public RentData(@JsonProperty("list_total_count") int listTotalCount,
                    @JsonProperty("row") List<RentRow> row) {
        this.listTotalCount = listTotalCount;
        this.row = row;
    }
}
