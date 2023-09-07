package com.ssafy.tott.api.seoul.batch.service;

import com.ssafy.tott.api.seoul.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
    private final SchedulerService schedulerService;

    @Scheduled(cron = "${schedules.cron.reward.publish}")/* 01:00 Start*/
    void runHouseDataJob() {
        schedulerService.fetchHouseData(100);
    }

}