package org.example.sensemon.application.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BusType {
    ANY(-1),
    I2C(0),
    ISA(1),
    PCI(2),
    SPI(3),
    VIRTUAL(4),
    ACPI(5),
    HID(6),
    MDIO(7),
    SCSI(8)
    ;
    private final int code;

    private static final Map<Integer, BusType> byCode = Arrays.stream(BusType.values())
            .collect(Collectors.toMap(BusType::getCode, UnaryOperator.identity()));

    public static BusType fromCode(int code) {
        return byCode.getOrDefault(code, BusType.ANY);
    }
}
