package com.ssafy.tott.housedetail.data.dto.response;

import com.ssafy.tott.housedetail.data.vo.HouseDetailRecentViewVO;
import com.ssafy.tott.member.domain.Member;
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

    public static HouseDetailRecentViewResponse toResponse(Member member, List<HouseDetailRecentViewVO> list) {
        return HouseDetailRecentViewResponse.builder()
                .memberId(member.getId())
                .houseDetailList(list)
                .resultCount(list.size())
                .build();
    }
}
