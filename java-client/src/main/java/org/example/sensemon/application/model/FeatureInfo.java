package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FeatureInfo {
    private String name;
    private int number;
    private FeatureType featureType;

    private DeviceInfo deviceInfo;

    @Override
    public String toString() {
        return "FeatureInfo { name = '%s', number = '%s', featureType = '%s' }".formatted(
                name, number, featureType);
    }
}
