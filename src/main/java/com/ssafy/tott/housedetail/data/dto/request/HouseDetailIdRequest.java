package com.ssafy.tott.housedetail.data.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseDetailIdRequest {
    private List<Integer> houseDetailIdList;
}
