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

    private FeatureInfo featureInfo;
}
