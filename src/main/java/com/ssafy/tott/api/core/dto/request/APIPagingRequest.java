package com.ssafy.tott.api.core.dto.request;

import com.ssafy.tott.api.core.dto.APIRequest;

public interface APIPagingRequest extends APIRequest {
    int getStart();

    int getEnd();

    int getSize();
}
