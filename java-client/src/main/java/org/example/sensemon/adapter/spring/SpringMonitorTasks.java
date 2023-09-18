package org.example.sensemon.adapter.spring;

import org.example.sensemon.application.service.SensorMonitorService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class SpringMonitorTasks {
    private final SensorMonitorService sensorMonitorService;

    public SpringMonitorTasks(SensorMonitorService sensorMonitorService) {
        this.sensorMonitorService = sensorMonitorService;
    }

    @Scheduled(
            initialDelay = 3,
            fixedRate = 1,
            timeUnit = TimeUnit.SECONDS)
    public void logSensorStatus() {
        sensorMonitorService.doLog();
    }

}
