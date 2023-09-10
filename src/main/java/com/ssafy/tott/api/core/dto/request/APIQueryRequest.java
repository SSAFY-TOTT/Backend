package com.ssafy.tott.api.core.dto.request;

import com.ssafy.tott.api.core.dto.APIRequest;

public interface APIQueryRequest extends APIRequest {
    String getQuery();
}
