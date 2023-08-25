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
public enum FeatureType {
    IN( 0x00),
    FAN( 0x01),
    TEMP( 0x02),
    POWER( 0x03),
    ENERGY( 0x04),
    CURR( 0x05),
    HUMIDITY( 0x06),
    MAX_MAIN( 0x07),
    VID(0x10),
    INTRUSION( 0x11),
    MAX_OTHER(0x12),
    BEEP_ENABLE(0x18),
    MAX (0x19),
    UNKNOWN	(Integer.MAX_VALUE),
    ;
    private final int code;

    private static final Map<Integer, FeatureType> byCode = Arrays.stream(FeatureType.values())
            .collect(Collectors.toMap(FeatureType::getCode, UnaryOperator.identity()));

    public static FeatureType fromCode(int code) {
        return byCode.getOrDefault(code, UNKNOWN);
    }
}
