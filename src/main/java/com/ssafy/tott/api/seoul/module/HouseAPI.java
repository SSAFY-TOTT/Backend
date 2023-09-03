package com.ssafy.tott.api.seoul.module;

import com.ssafy.tott.api.seoul.data.RentApiModel;
import com.ssafy.tott.api.seoul.data.RentRow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class HouseAPI {
    @Value("souldata.tbLnOpendataRentV.key")
    private String key;

    public RentApiModel fetchAPI(int start, int end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") );
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
        urlBuilder.append("/" + URLEncoder.encode("tbLnOpendataRentV","UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(urlBuilder.toString(), RentApiModel.class);
    }

    private List<RentRow> filteringRentHouse(RentApiModel rentApiModel){
        List<RentRow> result = rentApiModel.getTbLnOpendataRentV().getRow().stream()
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

        return result;
    }
}
