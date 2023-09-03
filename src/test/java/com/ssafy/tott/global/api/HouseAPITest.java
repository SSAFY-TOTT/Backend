package com.ssafy.tott.global.api;

import com.ssafy.tott.global.api.batch.model.RentApiModel;
import com.ssafy.tott.global.api.batch.model.RentRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class HouseAPITest{

    private String key = "sample";


    @DisplayName("api 연동 test")
    @Test
    public void connectAPI() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("tbLnOpendataRentV","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        RestTemplate restTemplate = new RestTemplate();
        RentApiModel rentApiModel = restTemplate.getForObject(urlBuilder.toString(), RentApiModel.class);
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
    }
}
