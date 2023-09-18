package org.example.sensemon.application.service;

import org.example.sensemon.adapter.SampleFilter;
import org.example.sensemon.application.model.SensorReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SensorMonitorService {
    private static final Logger log = LoggerFactory.getLogger(SensorMonitorService.class);
    public static final String TWO_DECIMAL_FORMAT = "%.2f";
    public static final String COMMA_SEPARATED = ", ";
    public static final String PREFIX = "[";
    public static final String SUFFIX = "]";

    private final SensorsService sensorsService;
    private final SampleFilter sampleFilter;
    private final List<? extends SensorReference> monitoredSensors;

    public SensorMonitorService(
            SensorsService sensorsService,
            SampleFilter sampleFilter,
            List<? extends SensorReference> monitoredSensors) {
        this.sensorsService = sensorsService;
        this.sampleFilter = sampleFilter;
        this.monitoredSensors = monitoredSensors;
    }

    public void doLog() {
        var filteredSample = sampleFilter.apply(monitoredSensors.stream()
                .map(sensorsService::getValue)
                .mapToDouble(data -> data.isSuccess() ? data.getValue() : Double.NaN)
                .toArray());
        var timestamp = LocalDateTime.now();

        log.info("SAMPLE {} : {}",
                timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                Arrays.stream(filteredSample)
                        .mapToObj(TWO_DECIMAL_FORMAT::formatted)
                        .collect(Collectors.joining(COMMA_SEPARATED, PREFIX, SUFFIX))
        );
    }
}
