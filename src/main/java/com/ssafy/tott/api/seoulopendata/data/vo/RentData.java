package com.ssafy.tott.api.seoulopendata.data.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RentData {
  private final int listTotalCount;
  private final List<RentRow> row;

  @JsonCreator
  public RentData(
      @JsonProperty("list_total_count") int listTotalCount,
      @JsonProperty("row") List<RentRow> row) {
    this.listTotalCount = listTotalCount;
    this.row = row;
  }
}
