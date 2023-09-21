package org.example.sensemon.adapter.jni;


import org.example.sensemon.adapter.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class JniSensorMonitor implements SensorMonitor, AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(JniSensorMonitor.class);
    private static final String RESOURCE_LIBJSENSORS_SO = "/libjsensors.so";

    static {
        log.info("Loading JNI native library...");
        try(var library = Objects.requireNonNull(JniSensorMonitor.class.getResourceAsStream(RESOURCE_LIBJSENSORS_SO))) {
            var target = Files.createTempFile("libsensors.so","");

            Files.copy(library, target, StandardCopyOption.REPLACE_EXISTING);
            System.load(target.toAbsolutePath().toString());
        } catch (IOException ex) {
            log.error("Unable to load libjsensors!", ex);
            throw new UnsatisfiedLinkError("Unable to load libjsensors!");
        }
    }

    private static boolean sensorsReady = false;
    private static native boolean sensorsInit();
    private static native void sensorsCleanup();
    private static native DeviceInfo[] getChipNames();
    private static native FeatureInfo[] getChipFeatures(DeviceInfo deviceInfo);
    private static native SubFeatureInfo[] getChipSubFeatures(FeatureInfo featureInfo);
    private static native SensorData getSubFeatureValue(SubFeatureInfo subFeatureInfo);

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
        var builder = sensorsReady
                ? getSubFeatureValue(subFeature).toBuilder()
                : SensorData.builder().success(false);

        return builder
                .subFeature(subFeature.getName())
                .feature(subFeature.getFeatureInfo().getName())
                .device(subFeature.getFeatureInfo().getDeviceInfo().getName())
                .build();
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
