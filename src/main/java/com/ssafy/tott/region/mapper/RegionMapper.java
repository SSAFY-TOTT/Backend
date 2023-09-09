package com.ssafy.tott.region.mapper;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.region.domain.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    public Region toEntity(RentRow row) {
        return Region.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .districtName(row.getSggNm())
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .legalDongName(row.getBjdongNm()).build();
    }
}
