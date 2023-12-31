package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SensorData {
    private String device;
    private String feature;
    private String subFeature;
    private boolean success;
    private double value;
}
