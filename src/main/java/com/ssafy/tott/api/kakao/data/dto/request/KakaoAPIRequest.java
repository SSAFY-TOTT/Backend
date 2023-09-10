package com.ssafy.tott.api.kakao.data.dto.request;

import com.ssafy.tott.api.core.dto.request.APIQueryRequest;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class KakaoAPIRequest implements APIQueryRequest {
    private String query;

    public static KakaoAPIRequest toRequest(String query) {
        return new KakaoAPIRequest(query);
    }
}
