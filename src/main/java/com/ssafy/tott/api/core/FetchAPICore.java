package com.ssafy.tott.api.core;

import com.ssafy.tott.api.core.dto.APIRequest;
import com.ssafy.tott.api.core.dto.APIResponse;

public interface FetchAPICore {
    APIResponse fetchAPI(APIRequest request);
}
