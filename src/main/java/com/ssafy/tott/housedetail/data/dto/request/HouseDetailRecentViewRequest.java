package com.ssafy.tott.housedetail.data.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class HouseDetailRecentViewRequest {
    private List<Integer> houseDetailIdList;
}
