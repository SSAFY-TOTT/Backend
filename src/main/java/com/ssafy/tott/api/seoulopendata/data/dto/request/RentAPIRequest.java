package com.ssafy.tott.api.seoulopendata.data.dto.request;

import com.ssafy.tott.api.core.dto.request.APIPagingRequest;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class RentAPIRequest implements APIPagingRequest {
    private int start;
    private int end;
    private int size;

    public static RentAPIRequest healthCheckRequest() {
        return RentAPIRequest.builder()
                .start(1)
                .end(1)
                .size(0)
                .build();
    }

    public static RentAPIRequest toRequest(int index, int size) {
        return RentAPIRequest.builder()
                .start(index)
                .end(index + size)
                .size(size)
                .build();
    }
}
