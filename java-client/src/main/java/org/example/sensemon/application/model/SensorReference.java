package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SensorReference {
    private int ordinal;
    private String device;
    private String feature;
    private String subFeature;
}
