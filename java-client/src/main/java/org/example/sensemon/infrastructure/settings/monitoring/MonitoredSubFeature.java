package org.example.sensemon.infrastructure.settings.monitoring;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonitoredSubFeature {
    private String name;
    private Integer ordinal;
}
