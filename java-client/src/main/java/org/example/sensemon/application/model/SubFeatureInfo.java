package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SubFeatureInfo {
    private String name;
    private int number;
    private SubFeatureType type;
    private int mapping;
    private int flags;
    private int systemId;

    private FeatureInfo featureInfo;

    @Override
    public String toString() {
        return ("SubFeatureInfo { " +
                "name = '%s'" +
                ", number = '%s'" +
                ", type = '%s'" +
                ", mapping = '%d'" +
                ", flags = '%d'" +
                ", systemId = '%d'" +
                " }").formatted(
                name, number, type, mapping, flags, systemId);
    }
}
