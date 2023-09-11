package com.ssafy.tott.api.core;

import java.time.LocalDateTime;

public abstract class FetchRetryImpl implements FetchRetry {
    @Override
    public boolean isTimeOut(LocalDateTime timeOut) {
        return timeOut.isAfter(LocalDateTime.now());
    }
}
