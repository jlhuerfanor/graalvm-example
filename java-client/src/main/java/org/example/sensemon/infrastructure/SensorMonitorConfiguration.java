package org.example.sensemon.infrastructure;

import org.example.sensemon.adapter.SampleFilter;
import org.example.sensemon.application.model.SensorReference;
import org.example.sensemon.application.service.SensorMonitorService;
import org.example.sensemon.application.service.SensorsService;
import org.example.sensemon.infrastructure.settings.MonitoringSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class SensorMonitorConfiguration {
    @Bean
    public SensorMonitorService sensorMonitorService(
            SensorsService sensorsService,
            SampleFilter sampleFilter,
            MonitoringSettings monitoringSettings) {
        var sensorReferences = monitoringSettings.getDevices().stream()
                .flatMap(device -> device.getFeatures().stream()
                        .flatMap(feature -> feature.getSubFeatures().stream()
                                .map(subFeatures -> SensorReference.builder()
                                        .ordinal(subFeatures.getOrdinal())
                                        .device(device.getName())
                                        .feature(feature.getName())
                                        .subFeature(subFeatures.getName())
                                        .build())))
                .sorted(Comparator.comparing(SensorReference::getOrdinal))
                .toList();

        return new SensorMonitorService(sensorsService, sampleFilter, sensorReferences);
    }
}
