package com.ssafy.tott.region.fixture;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
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
    CREATE_REGION_BY_ROW(0, "", 0, ""),
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
                .districtCode(districtCode)
                .districtName(districtName)
                .legalDongCode(legalDongCode)
                .legalDongName(legalDongName)
                .build();
    }

    public Region toRegion(RentRow row) {
        return Region.builder()
                .districtName(row.getSggNm())
                .districtCode(Integer.parseInt(row.getSggCd()))
                .legalDongName(row.getBjdongNm())
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .build();
    }
}
