package com.ssafy.tott.region.data.dto.response;

import com.ssafy.tott.region.data.vo.DistrictVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class DistrictResponse {
    private List<DistrictVO> districtList;
    private int resultCount;

    public static DistrictResponse from(List<DistrictVO> list) {
        return DistrictResponse.builder()
                .districtList(list)
                .resultCount(list.size())
                .build();
    }
}
