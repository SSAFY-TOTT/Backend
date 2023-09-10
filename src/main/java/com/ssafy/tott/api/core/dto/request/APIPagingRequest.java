package com.ssafy.tott.api.core.dto.request;

public interface APIPagingRequest {
    int getStart();

    int getEnd();

    int getSize();
}
