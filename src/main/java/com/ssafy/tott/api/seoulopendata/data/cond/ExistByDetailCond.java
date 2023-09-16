package com.ssafy.tott.api.seoulopendata.data.cond;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class ExistByDetailCond {
    private int districtCode;
    private int legalDongCode;
    private int mainNumber;
    private int subNumber;
    private double area;
    private int floor;
    private int price;

    public static ExistByDetailCond from(RentRow row) {
        return ExistByDetailCond.builder()
                .districtCode(Integer.parseInt(row.getSggCd()))
                .legalDongCode(Integer.parseInt(row.getBjdongCd()))
                .mainNumber(Integer.parseInt(row.getBobn()))
                .subNumber(Integer.parseInt(row.getBubn()))
                .area(row.getRentArea())
                .floor(row.getFlrNo())
                .price(Integer.parseInt(row.getRentGtn()))
                .build();
    }
}
