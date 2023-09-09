package com.ssafy.tott.housedetail.mapper;

import com.ssafy.tott.api.seoul.data.RentRow;
import com.ssafy.tott.housedetail.domain.HouseDetail;
import com.ssafy.tott.housegeo.domain.HouseGeo;
import org.springframework.stereotype.Component;

@Component
public class HouseDetailMapper {
    public HouseDetail toEntity(RentRow row, HouseGeo houseGeo) {
        return HouseDetail.builder()
                .houseGeo(houseGeo)
                .price(Integer.parseInt(row.getRentGtn()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .build();
    }
}
