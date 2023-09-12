package com.ssafy.tott.api.core;

import java.time.LocalDateTime;

public interface FetchRetry {
    boolean isTimeOut(LocalDateTime timeOut);
}
