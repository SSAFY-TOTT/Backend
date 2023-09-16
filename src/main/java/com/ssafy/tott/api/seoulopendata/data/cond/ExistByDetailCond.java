package com.ssafy.tott.api.seoulopendata.data.cond;

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
}
