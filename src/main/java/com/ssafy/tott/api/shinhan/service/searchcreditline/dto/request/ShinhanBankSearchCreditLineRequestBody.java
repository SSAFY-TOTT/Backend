package com.ssafy.tott.api.shinhan.service.searchcreditline.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.tott.api.shinhan.dto.ShinhanBankDataBody;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShinhanBankSearchCreditLineRequestBody extends ShinhanBankDataBody {
    @JsonProperty()
    private String serviceCode;

    @JsonProperty("연동거래정보")
    private String linkedTransactionInformation;

    @JsonProperty("주택소재지코드")
    private String housingLocationCode;

    @JsonProperty("보증금액")
    private String rentGtn;

    @JsonProperty("연소득")
    private String annualIncome;

    private ShinhanBankSearchCreditLineRequestBody(
            String serviceCode,
            String linkedTransactionInformation,
            String housingLocationCode,
            String rentGtn,
            String annualIncome) {
        this.serviceCode = serviceCode;
        this.linkedTransactionInformation = linkedTransactionInformation;
        this.housingLocationCode = housingLocationCode;
        this.rentGtn = rentGtn;
        this.annualIncome = annualIncome;
    }

    public static ShinhanBankSearchCreditLineRequestBody of(
            String serviceCode,
            String linkedTransactionInformation,
            String housingLocationCode,
            String rentGtn,
            String annualIncome) {
        return new ShinhanBankSearchCreditLineRequestBody(
                serviceCode,
                linkedTransactionInformation,
                housingLocationCode,
                rentGtn,
                annualIncome);
    }
}
