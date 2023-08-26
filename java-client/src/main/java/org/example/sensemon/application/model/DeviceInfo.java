package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DeviceInfo {
    private String prefix;
    private DeviceBus bus;
    private String path;
    private int address;
    private String name;
    private int systemId;

    @Override
    public String toString() {
        return ("DeviceInfo { name = '%s'" +
                ", prefix = '%s'" +
                ", bus = %s" +
                ", path = '%s'" +
                ", address = '%d'" +
                ", systemId = '%d'" +
                "}").formatted(name, prefix, bus, path, address, systemId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceInfo that = (DeviceInfo) o;
        return systemId == that.systemId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, systemId);
    }
}
