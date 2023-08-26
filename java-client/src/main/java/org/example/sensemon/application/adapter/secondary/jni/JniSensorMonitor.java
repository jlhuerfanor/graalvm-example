package org.example.sensemon.application.adapter.secondary.jni;


import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class JniSensorMonitor implements SensorMonitor, AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(JniSensorMonitor.class);

    static {
        log.info("Loading native library...");
        System.load("/home/jhuerfano/git/endava/graalvm/java-client/src/main/native/build/libjsensors.so");
    }

    private static boolean sensorsReady = false;
    private static native boolean sensorsInit();
    private static native void sensorsCleanup();
    private static native DeviceInfo[] getChipNames();
    private static native FeatureInfo[] getChipFeatures(DeviceInfo deviceInfo);
    private static native SubFeatureInfo[] getChipSubFeatures(FeatureInfo featureInfo);
    private static native double getSubFeatureValue(SubFeatureInfo subFeatureInfo);

    private List<DeviceInfo> sensors;
    private final Map<String, List<FeatureInfo>> featureInfoMap = new ConcurrentHashMap<>();
    private final Map<FeatureKey, List<SubFeatureInfo>> subfeatureInfoMap = new ConcurrentHashMap<>();

    public JniSensorMonitor() {
        sensorsReady = sensorsReady || sensorsInit();
        log.atLevel(sensorsReady ? Level.INFO : Level.ERROR).log("Initialized sensors");
    }

    @Override
    public List<DeviceInfo> getDevices() {
        if(sensorsReady && Objects.isNull(sensors)) {
            sensors = Arrays.stream(getChipNames()).toList();
        }

        return sensors;
    }

    @Override
    public List<FeatureInfo> getFeatures(DeviceInfo device) {
        if(sensorsReady) {
            return featureInfoMap.computeIfAbsent(device.getName(), (unused) -> Optional.of(device)
                    .map(JniSensorMonitor::getChipFeatures)
                    .map(Arrays::stream)
                    .map(Stream::toList)
                    .orElse(null));
        } else return Collections.emptyList();
    }

    @Override
    public List<SubFeatureInfo> getSubFeatures(FeatureInfo feature) {
        if(sensorsReady) {
            var key = new FeatureKey(feature.getDeviceInfo().getName(), feature.getNumber());

            return subfeatureInfoMap.computeIfAbsent(key, (unused) -> Optional.of(feature)
                    .map(JniSensorMonitor::getChipSubFeatures)
                    .map(Arrays::stream)
                    .map(Stream::toList)
                    .orElse(null));
        } return Collections.emptyList();
    }

    @Override
    public SensorData getValue(SubFeatureInfo subFeature) {
        if(sensorsReady) {
            double value = getSubFeatureValue(subFeature);

            return SensorData.builder()
                    .failed(Double.isNaN(value))
                    .value(value)
                    .build();
        } else return SensorData.builder().failed(true).build();
    }

    @Override
    public void close() {
        sensorsCleanup();
        log.info("Cleaned up sensors");
    }

    private record FeatureKey(String deviceName, int featureNumber) implements Comparable<FeatureKey> {
        @Override
        public int compareTo(FeatureKey other) {
            var comparator = Comparator.<String>naturalOrder();
            var deviceNameComparison = comparator.compare(deviceName, other.deviceName);

            return deviceNameComparison == 0
                    ? Integer.compare(featureNumber, other.featureNumber)
                    : deviceNameComparison;
        }
    }
}
