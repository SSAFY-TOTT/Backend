package com.ssafy.tott.api.kakao.service;

import com.ssafy.tott.api.core.FetchAPICore;
import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.core.dto.APIResponse;
import com.ssafy.tott.api.kakao.data.dto.request.KakaoAPIRequest;
import com.ssafy.tott.api.kakao.data.dto.response.KakaoAPIResponse;
import com.ssafy.tott.api.kakao.data.vo.Documents;
import com.ssafy.tott.api.kakao.property.KakaoAddressProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoMapFetchAPI implements FetchAPICore {
    private final WebClient kakaoWebClient;
    private final KakaoAddressProperties kakaoAddressProperties;

    /**
     * 파라미터에 맞는 주소의 좌표(위도, 경도)를 추출한다.
     *
     * @param sggNm    법정구 이름
     * @param bjDongNm 법정동 이름
     * @param bobn     본번
     * @param bubn     부번
     * @return x(경도), y(위도)를 return
     * @throws IndexOutOfBoundsException 해당 주소의 좌표를 찾을 수 없으면 던지는 Exception
     */
    public Documents kakaoAddressSearch(String sggNm, String bjDongNm, String bobn, String bubn)
            throws IndexOutOfBoundsException {
        /* roadAddress : 지번 ( 구 / 동 / 본번-부번 ) */
        KakaoAPIRequest request = makeRoadAddress(sggNm, bjDongNm, bobn, bubn);
        KakaoAPIResponse kakaoAPIResponse = (KakaoAPIResponse) fetchAPI(request);
        validateToAPIResponse(kakaoAPIResponse);
        return kakaoAPIResponse.getDocuments().get(0);
    }

    private KakaoAPIRequest makeRoadAddress(String sggNm, String bjDongNm, String bobn, String bubn) {
        StringBuilder sb = new StringBuilder()
                .append(sggNm).append(" ")
                .append(bjDongNm).append(" ")
                .append(bobn).append("-")
                .append(bubn);
        return KakaoAPIRequest.toRequest(sb.toString());
    }

    @Override
    public APIResponse fetchAPI(APIRequest request) {
        KakaoAPIRequest kakaoAPIRequest = (KakaoAPIRequest) request;
        return kakaoWebClient
                .method(kakaoAddressProperties.getMethod())
                .uri(
                        builder ->
                                builder
                                        .path(kakaoAddressProperties.getPath())
                                        .queryParam("query", kakaoAPIRequest.getQuery())
                                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(KakaoAPIResponse.class)
                .block();
    }

    private void validateToAPIResponse(KakaoAPIResponse kakaoAPIResponse) {
        if (kakaoAPIResponse.getDocuments().isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
