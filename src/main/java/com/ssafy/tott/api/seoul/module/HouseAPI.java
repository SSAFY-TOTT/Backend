package com.ssafy.tott.api.seoul.module;

import com.ssafy.tott.api.seoul.data.RentApiModel;
import com.ssafy.tott.api.seoul.data.RentRow;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HouseAPI {
    @Value("${seouldata.tbLnOpendataRentV.key}")
    private String key;

    public RentApiModel fetchAPI(int start, int end) {
        String urlBuilder = "http://openapi.seoul.go.kr:8088" + '/' + URLEncoder.encode(key, StandardCharsets.UTF_8) +
                '/' + URLEncoder.encode("json", StandardCharsets.UTF_8) +
                '/' + URLEncoder.encode("tbLnOpendataRentV", StandardCharsets.UTF_8) +
                '/' + URLEncoder.encode(String.valueOf(start), StandardCharsets.UTF_8) +
                '/' + URLEncoder.encode(String.valueOf(end), StandardCharsets.UTF_8);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlBuilder, RentApiModel.class);
    }

    public List<RentRow> filteringRentHouse(RentApiModel rentApiModel) {
        return rentApiModel.getTbLnOpendataRentV().getRow().stream()
                .filter(row -> row.getRentGbn().equals("전세"))
                .filter(row -> row.getCntrctPrd().equals(""))
                .filter(row -> row.getBobn() != null && !row.getBobn().equals(""))
                .filter(row -> row.getBubn() != null && !row.getBubn().equals(""))
                .filter(row -> row.getBjdongCd() != null && !row.getBjdongCd().equals(""))
                .filter(row -> row.getBjdongNm() != null && !row.getBjdongNm().equals(""))
                .filter(row -> row.getSggCd() != null && !row.getSggCd().equals(""))
                .filter(row -> row.getSggNm() != null && !row.getSggNm().equals(""))
                .filter(row -> row.getRentGtn() != null && !row.getRentGtn().equals("0"))
                .filter(row -> row.getBuildYear() != null && !row.getBuildYear().equals(""))
                .filter(row -> row.getRentArea() != null && row.getRentArea() != 0D)
                .filter(row -> row.getFlrNo() != null && row.getFlrNo() != 0D)
                .collect(Collectors.toList());
    }
}
