package com.yewai.StudentRegistration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class ScheduledTasks {
    @Scheduled(cron = "0 0 * * * *")
    public void performTask() {
        log.info("Scheduled task executed every hour.");
    }
}
