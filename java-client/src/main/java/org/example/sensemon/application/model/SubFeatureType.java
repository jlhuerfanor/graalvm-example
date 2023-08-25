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
public enum SubFeatureType {
    IN_INPUT(0),
    IN_MIN(1),
    IN_MAX(2),
    IN_LCRIT(3),
    IN_CRIT(4),
    IN_AVERAGE(5),
    IN_LOWEST(6),
    IN_HIGHEST(7),
    IN_ALARM(128),
    IN_MIN_ALARM(129),
    IN_MAX_ALARM(130),
    IN_BEEP(131),
    IN_LCRIT_ALARM(132),
    IN_CRIT_ALARM(133),
    FAN_INPUT(256),
    FAN_MIN(257),
    FAN_MAX(258),
    FAN_ALARM(384),
    FAN_FAULT(385),
    FAN_DIV(386),
    FAN_BEEP(387),
    FAN_PULSES(388),
    FAN_MIN_ALARM(389),
    FAN_MAX_ALARM(390),
    TEMP_INPUT(512),
    TEMP_MAX(513),
    TEMP_MAX_HYST(514),
    TEMP_MIN(515),
    TEMP_CRIT(516),
    TEMP_CRIT_HYST(517),
    TEMP_LCRIT(518),
    TEMP_EMERGENCY(519),
    TEMP_EMERGENCY_HYST(520),
    TEMP_LOWEST(521),
    TEMP_HIGHEST(522),
    TEMP_MIN_HYST(523),
    TEMP_LCRIT_HYST(524),
    TEMP_ALARM(640),
    TEMP_MAX_ALARM(641),
    TEMP_MIN_ALARM(642),
    TEMP_CRIT_ALARM(643),
    TEMP_FAULT(644),
    TEMP_TYPE(645),
    TEMP_OFFSET(646),
    TEMP_BEEP(647),
    TEMP_EMERGENCY_ALARM(648),
    TEMP_LCRIT_ALARM(649),
    POWER_AVERAGE(768),
    POWER_AVERAGE_HIGHEST(769),
    POWER_AVERAGE_LOWEST(770),
    POWER_INPUT(771),
    POWER_INPUT_HIGHEST(772),
    POWER_INPUT_LOWEST(773),
    POWER_CAP(774),
    POWER_CAP_HYST(775),
    POWER_MAX(776),
    POWER_CRIT(777),
    POWER_MIN(778),
    POWER_LCRIT(779),
    POWER_AVERAGE_INTERVAL(896),
    POWER_ALARM(897),
    POWER_CAP_ALARM(898),
    POWER_MAX_ALARM(899),
    POWER_CRIT_ALARM(900),
    POWER_MIN_ALARM(901),
    POWER_LCRIT_ALARM(902),
    ENERGY_INPUT(1024),
    CURR_INPUT(1280),
    CURR_MIN(1281),
    CURR_MAX(1282),
    CURR_LCRIT(1283),
    CURR_CRIT(1284),
    CURR_AVERAGE(1285),
    CURR_LOWEST(1286),
    CURR_HIGHEST(1287),
    CURR_ALARM(1408),
    CURR_MIN_ALARM(1409),
    CURR_MAX_ALARM(1410),
    CURR_BEEP(1411),
    CURR_LCRIT_ALARM(1412),
    CURR_CRIT_ALARM(1413),
    HUMIDITY_INPUT(1536),
    VID(4096),
    INTRUSION_ALARM(4352),
    INTRUSION_BEEP(4353),
    BEEP_ENABLE(6144),
    UNKNOWN(Integer.MAX_VALUE)
    ;
    private final int code;

    private static final Map<Integer, SubFeatureType> byCode = Arrays.stream(SubFeatureType.values())
            .collect(Collectors.toMap(SubFeatureType::getCode, UnaryOperator.identity()));

    public static SubFeatureType fromCode(int code) {
        return byCode.getOrDefault(code, UNKNOWN);
    }
}
