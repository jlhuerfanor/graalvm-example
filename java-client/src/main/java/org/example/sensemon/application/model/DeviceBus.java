package org.example.sensemon.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DeviceBus {
    private BusType type;
    private int number;

    @Override
    public String toString() {
        return "{ type = '%s', number = '%d' }".formatted(type, number);
    }
}
