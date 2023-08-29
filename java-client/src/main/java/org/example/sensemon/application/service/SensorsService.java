package org.example.sensemon.application.service;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SensorsService {
    private final SensorMonitor monitor;
    private final Map<String, DeviceInfo> deviceInfoMap = new HashMap<>();
    private final Map<DeviceInfo, Map<String, FeatureInfo>> featureInfoMap = new HashMap<>();
    private final Map<FeatureInfo, Map<String, SubFeatureInfo>> subFeatureInfoMap = new HashMap<>();

    public SensorsService(SensorMonitor monitor) {
        this.monitor = monitor;
    }

    public DeviceInfo getDevice(String deviceName) {
        return checkDeviceCache().get(deviceName);
    }
    public FeatureInfo getFeature(String deviceName, String featureName) {
        return Optional.of(getDevice(deviceName))
                .map(this::checkFeatureCache)
                .map(cache -> cache.get(featureName))
                .orElse(null);
    }
    public SubFeatureInfo getSubFeature(String deviceName, String featureName, String subFeatureName) {
        return Optional.of(getFeature(deviceName, featureName))
                .map(this::checkSubFeatureCache)
                .map(cache -> cache.get(subFeatureName))
                .orElse(null);
    }

    public List<DeviceInfo> getDevices() {
        return checkDeviceCache().values().stream().toList();
    }
    public List<FeatureInfo> getFeatures(String deviceName) {
        return Optional.of(deviceName)
                .map(this::getDevice)
                .map(this::checkFeatureCache)
                .map(Map::values)
                .map(Collection::stream)
                .map(Stream::toList)
                .orElse(Collections.emptyList());
    }
    public List<SubFeatureInfo> getSubFeatures(String deviceName, String featureName) {
        return Optional.of(this.getFeature(deviceName, featureName))
                .map(this::checkSubFeatureCache)
                .map(Map::values)
                .map(Collection::stream)
                .map(Stream::toList)
                .orElse(Collections.emptyList());
    }

    public SensorData getValue(String deviceName, String featureName, String subFeatureName) {
        var device = checkDeviceCache().get(deviceName);
        var feature = checkFeatureCache(device).get(featureName);

        return monitor.getValue(checkSubFeatureCache(feature).get(subFeatureName));
   }

    private Map<String, DeviceInfo> checkDeviceCache() {
        if(deviceInfoMap.isEmpty()) {
            monitor.getDevices().forEach(deviceInfo -> deviceInfoMap.put(deviceInfo.getName(), deviceInfo));
        }
        return deviceInfoMap;
    }
    private Map<String, FeatureInfo> checkFeatureCache(DeviceInfo device) {
        return featureInfoMap.computeIfAbsent(device, unused -> monitor.getFeatures(device).stream().collect(Collectors.toMap(
                FeatureInfo::getName,
                UnaryOperator.identity())));
    }
    private Map<String, SubFeatureInfo> checkSubFeatureCache(FeatureInfo feature) {
        return subFeatureInfoMap.computeIfAbsent(feature, unused -> monitor.getSubFeatures(feature).stream().collect(Collectors.toMap(
                SubFeatureInfo::getName,
                UnaryOperator.identity())));
    }
}
