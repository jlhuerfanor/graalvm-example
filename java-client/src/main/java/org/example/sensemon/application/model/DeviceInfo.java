package org.example.sensemon.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DeviceInfo {
    private String prefix;
    private DeviceBus bus;
    private String path;
    private int address;
    private String name;

    @Getter(AccessLevel.NONE)
    private int systemId;

    @Override
    public String toString() {
        return ("DeviceInfo { name = '%s', " +
                "prefix = '%s', " +
                "bus = %s, " +
                "path = '%s'," +
                "address = '%d'}").formatted(name, prefix, bus, path, address);
    }
}
