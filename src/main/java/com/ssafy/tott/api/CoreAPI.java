package com.ssafy.tott.api;

import com.ssafy.tott.api.shinhan.dto.response.ShinhanBankAPIResponse;

public interface CoreAPI {
    APIResponse fetchAPI(String json);
}
