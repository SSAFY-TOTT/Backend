package com.ssafy.tott.housedetail.data.cond;

import com.ssafy.tott.housedetail.data.dto.request.HouseDetailRecentViewRequest;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailRecentViewCond {
    private List<Integer> housedetailIdList;

    public static HouseDetailRecentViewCond toCond(HouseDetailRecentViewRequest request) {
        return HouseDetailRecentViewCond.builder()
                .housedetailIdList(request.getHouseDetailIdList())
                .build();
    }
}
