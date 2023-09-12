package com.ssafy.tott.api.seoulopendata.data.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RentRow {
    private final String accYear;
    private final String sggCd;
    private final String sggNm;
    private final String bjdongCd;
    private final String bjdongNm;
    private final String landGbn;
    private final String landGbnNm;
    private final String bobn;
    private final String bubn;
    private final Integer flrNo;
    private final String cntrctDe;
    private final String rentGbn;
    private final Double rentArea;
    private final String rentGtn;
    private final String rentFee;
    private final String bldgNm;
    private final String buildYear;
    private final String houseGbnNm;
    private final String cntrctPrd;
    private final String newRonSecd;
    private final String cntrctUpdtRqestAt;
    private final String beforeGrntyAmount;
    private final String beforeMtRentChrge;

    @JsonCreator
    public RentRow(
            @JsonProperty("ACC_YEAR") String accYear,
            @JsonProperty("SGG_CD") String sggCd,
            @JsonProperty("SGG_NM") String sggNm,
            @JsonProperty("BJDONG_CD") String bjdongCd,
            @JsonProperty("BJDONG_NM") String bjdongNm,
            @JsonProperty("LAND_GBN") String landGbn,
            @JsonProperty("LAND_GBN_NM") String landGbnNm,
            @JsonProperty("BOBN") String bobn,
            @JsonProperty("BUBN") String bubn,
            @JsonProperty("FLR_NO") Integer flrNo,
            @JsonProperty("CNTRCT_DE") String cntrctDe,
            @JsonProperty("RENT_GBN") String rentGbn,
            @JsonProperty("RENT_AREA") Double rentArea,
            @JsonProperty("RENT_GTN") String rentGtn,
            @JsonProperty("RENT_FEE") String rentFee,
            @JsonProperty("BLDG_NM") String bldgNm,
            @JsonProperty("BUILD_YEAR") String buildYear,
            @JsonProperty("HOUSE_GBN_NM") String houseGbnNm,
            @JsonProperty("CNTRCT_PRD") String cntrctPrd,
            @JsonProperty("NEW_RON_SECD") String newRonSecd,
            @JsonProperty("CNTRCT_UPDT_RQEST_AT") String cntrctUpdtRqestAt,
            @JsonProperty("BEFORE_GRNTY_AMOUNT") String beforeGrntyAmount,
            @JsonProperty("BEFORE_MT_RENT_CHRGE") String beforeMtRentChrge) {
        this.accYear = accYear;
        this.sggCd = sggCd;
        this.sggNm = sggNm;
        this.bjdongCd = bjdongCd;
        this.bjdongNm = bjdongNm;
        this.landGbn = landGbn;
        this.landGbnNm = landGbnNm;
        this.bobn = bobn;
        this.bubn = bubn;
        this.flrNo = flrNo;
        this.cntrctDe = cntrctDe;
        this.rentGbn = rentGbn;
        this.rentArea = rentArea;
        this.rentGtn = rentGtn;
        this.rentFee = rentFee;
        this.bldgNm = bldgNm;
        this.buildYear = buildYear;
        this.houseGbnNm = houseGbnNm;
        this.cntrctPrd = cntrctPrd;
        this.newRonSecd = newRonSecd;
        this.cntrctUpdtRqestAt = cntrctUpdtRqestAt;
        this.beforeGrntyAmount = beforeGrntyAmount;
        this.beforeMtRentChrge = beforeMtRentChrge;
    }
}
