package com.ssafy.tott.api.core;

public interface FetchAPICore {
    default APIResponse fetchAPI(String request) {
        return null;
    }

    default APIResponse fetchAPI(int arg1, int arg2) {
        return null;
    }
}
