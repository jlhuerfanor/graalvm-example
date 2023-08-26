package org.example.sensemon.application.adapter.secondary.jni;


import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class JniSensorMonitor implements SensorMonitor, AutoCloseable {
    static {
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

    public JniSensorMonitor() throws IOException {
        sensorsReady = sensorsReady || sensorsInit();

        if(!sensorsReady) {
            throw new IOException("Sensors library initialization error");
        }
    }

    @Override
    public List<DeviceInfo> getDevices() {
        if(Objects.isNull(sensors)) {
            sensors = Arrays.stream(getChipNames()).toList();
        }

        return sensors;
    }

    @Override
    public List<FeatureInfo> getFeatures(DeviceInfo device) {
        return featureInfoMap.computeIfAbsent(device.getName(), (unused) -> Optional.of(device)
                .map(JniSensorMonitor::getChipFeatures)
                .map(Arrays::stream)
                .map(Stream::toList)
                .orElse(null));
    }

    @Override
    public List<SubFeatureInfo> getSubFeatures(FeatureInfo feature) {
        var key = new FeatureKey(feature.getDeviceInfo().getName(), feature.getNumber());

        return subfeatureInfoMap.computeIfAbsent(key, (unused) -> Optional.of(feature)
                .map(JniSensorMonitor::getChipSubFeatures)
                .map(Arrays::stream)
                .map(Stream::toList)
                .orElse(null));
    }

    @Override
    public SensorData getValue(SubFeatureInfo subFeature) {
        double value = getSubFeatureValue(subFeature);

        return SensorData.builder()
                .failed(Double.isNaN(value))
                .value(value)
                .build();
    }

    @Override
    public void close() {
        sensorsCleanup();
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
