package com.ssafy.tott.api.seoul.batch;

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


//    @Scheduled(cron = "${schedules.cron.reward.publish}")/* 01:00 Start*/
    @Scheduled(fixedDelay = 1000*60*60*24,initialDelay = 3000)   /* 3초후 시작, 24시간 후 업데이트 */
    void runHouseDataJob() {
        schedulerService.fetchHouseData(100);
    }

}
