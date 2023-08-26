package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FeatureInfo {
    private String name;
    private int number;
    private FeatureType featureType;
    private int systemId;

    private DeviceInfo deviceInfo;

    @Override
    public String toString() {
        return ("FeatureInfo { " +
                "name = '%s'" +
                ", number = '%s'" +
                ", featureType = '%s'" +
                ", systemId = '%d' }").formatted(
                name, number, featureType, systemId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureInfo that = (FeatureInfo) o;
        return number == that.number && Objects.equals(name, that.name) && featureType == that.featureType && Objects.equals(deviceInfo, that.deviceInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, featureType, deviceInfo);
    }
}
