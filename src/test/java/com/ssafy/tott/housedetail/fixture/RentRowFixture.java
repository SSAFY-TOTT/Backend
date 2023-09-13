package com.ssafy.tott.housedetail.fixture;

import com.ssafy.tott.api.seoulopendata.data.vo.RentRow;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum RentRowFixture {
    HOUSE_DETAIL_ONE("2023", "11380", "은평구", "10300", "불광동", "1",
            "대지", "0105", "0076", 3, "20230901", "전세", 57.76,
            "23000", "0", "105-76", "2018", "연립다세대",
            "", "신규", "", "0", ""),
    ;
    private String accYear;
    private String sggCd;
    private String sggNm;
    private String bjdongCd;
    private String bjdongNm;
    private String landGbn;
    private String landGbnNm;
    private String bobn;
    private String bubn;
    private Integer flrNo;
    private String cntrctDe;
    private String rentGbn;
    private Double rentArea;
    private String rentGtn;
    private String rentFee;
    private String bldgNm;
    private String buildYear;
    private String houseGbnNm;
    private String cntrctPrd;
    private String newRonSecd;
    private String cntrctUpdtRqestAt;
    private String beforeGrntyAmount;
    private String beforeMtRentChrge;

    public RentRow toRentRow() {
        return new RentRow(accYear, sggCd, sggNm, bjdongCd, bjdongNm, landGbn, landGbnNm, bobn, bubn, flrNo, cntrctDe
                , rentGbn, rentArea, rentGtn, rentFee, bldgNm, buildYear, houseGbnNm, cntrctPrd, newRonSecd,
                cntrctUpdtRqestAt, beforeGrntyAmount, beforeMtRentChrge);
    }
}
