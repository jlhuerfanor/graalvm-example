package org.example.sensemon.infrastructure.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sensemon.infrastructure.settings.monitoring.MonitoredDevice;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MonitoringSettings {
    private List<MonitoredDevice> devices;
}
