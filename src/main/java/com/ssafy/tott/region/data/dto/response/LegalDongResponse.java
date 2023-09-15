package com.ssafy.tott.region.data.dto.response;

import com.ssafy.tott.region.data.vo.LegalDongVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class LegalDongResponse {
    private List<LegalDongVO> legalDongList;
    private int resultCount;

    public static LegalDongResponse from(List<LegalDongVO> list) {
        return LegalDongResponse.builder()
                .legalDongList(list)
                .resultCount(list.size())
                .build();
    }
}
