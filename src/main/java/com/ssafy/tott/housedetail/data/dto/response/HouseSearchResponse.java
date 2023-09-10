package com.ssafy.tott.housedetail.data.dto.response;

import com.ssafy.tott.housedetail.data.vo.HouseSearchGeoVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HouseSearchResponse {
    private List<HouseSearchGeoVO> houseGeoList;
    private int houseGeoCount;
}
