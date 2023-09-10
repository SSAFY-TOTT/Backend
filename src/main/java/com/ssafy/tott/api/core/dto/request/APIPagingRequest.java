package com.ssafy.tott.api.core.dto.request;

public interface APIPagingRequest extends APIJsonRequest {
    int getStart();

    int getEnd();

    int getSize();
}
