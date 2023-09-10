package com.ssafy.tott.housegeo.data.dto.response;

import com.ssafy.tott.housegeo.data.vo.HouseSearchGeoVO;
import com.ssafy.tott.region.domain.Region;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class HouseGeoSearchResponse {
    private String regionName;  // 자치구 + 법정동
    private List<HouseSearchGeoVO> houseGeoList;
    private int houseGeoCount;

    public static HouseGeoSearchResponse of(Region region, List<HouseSearchGeoVO> list) {
        return HouseGeoSearchResponse.builder()
                .regionName(String.format("%s %s", region.getDistrictName(), region.getLegalDongName()))
                .houseGeoList(list)
                .houseGeoCount(list.size())
                .build();
    }
}
