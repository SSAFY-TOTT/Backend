package com.ssafy.tott.housedetail.data.dto.response;

import com.ssafy.tott.housedetail.data.vo.HouseDetailRecentViewVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseDetailRecentViewResponse {
    private int memberId;
    private List<HouseDetailRecentViewVO> houseDetailList;
    private int resultCount;
}
