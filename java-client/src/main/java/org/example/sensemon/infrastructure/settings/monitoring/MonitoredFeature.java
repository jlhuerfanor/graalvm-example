package org.example.sensemon.infrastructure.settings.monitoring;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MonitoredFeature {
    private String name;
    private List<MonitoredSubFeature> subFeatures;
}
