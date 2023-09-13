package com.ssafy.tott.region.fixture;

import com.ssafy.tott.region.data.cond.RegionFilterCond;
import com.ssafy.tott.region.domain.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum RegionFixture {
    REGION_ONE(11170, "용산구", 10100, "후암동"),
    REGION_TWO(11290, "성북구", 10300, "돈암동"),
    REGION_THREE(11680, "강남구", 10600, "대치동"),
    ;
    private int districtCode;
    private String districtName;
    private int legalDongCode;
    private String legalDongName;

    public Region toRegion() {
        return new Region(districtCode, districtName, legalDongCode, legalDongName);
    }

    public RegionFilterCond toCond() {
        return RegionFilterCond.builder()
                .legalDongCode(districtCode)
                .districtName(districtName)
                .legalDongName(legalDongName)
                .districtCode(legalDongCode)
                .build();
    }
}
