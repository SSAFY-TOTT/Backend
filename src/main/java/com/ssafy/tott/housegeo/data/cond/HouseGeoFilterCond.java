package com.ssafy.tott.housegeo.data.cond;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseGeoFilterCond {
  private String district;
  private String legalDong;
  private int minPrice;
  private int maxPrice;
  private int minArea;
  private int maxArea;
  private List<String> type;
  private int buildingYear;
}
