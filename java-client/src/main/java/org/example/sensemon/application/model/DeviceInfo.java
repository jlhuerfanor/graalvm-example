package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DeviceInfo {
    private String prefix;
    private DeviceBus bus;
    private String path;
    private int address;
    private String name;

    @Override
    public String toString() {
        return ("{ name = '%s', " +
                "prefix = '%s', " +
                "bus = %s, " +
                "path = '%s'," +
                "address = '%d'}").formatted(name, prefix, bus, path, address);
    }
}