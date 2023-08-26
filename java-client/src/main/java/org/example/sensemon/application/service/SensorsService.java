package org.example.sensemon.application.service;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
public class SensorsService {
    private final SensorMonitor monitor;
    private final Map<String, DeviceInfo> deviceInfoMap = new HashMap<>();
    private final Map<DeviceInfo, Map<Integer, FeatureInfo>> featureInfoMap = new HashMap<>();
    private final Map<FeatureInfo, Map<Integer, SubFeatureInfo>> subFeatureInfoMap = new HashMap<>();

    public SensorsService(SensorMonitor monitor) {
        this.monitor = monitor;
    }

    public DeviceInfo getDevice(String deviceName) {
        return checkDeviceCache().get(deviceName);
    }
    public FeatureInfo getFeature(String deviceName, Integer deviceNumber) {
        var device = checkDeviceCache().get(deviceName);

        return checkFeatureCache(device).get(deviceNumber);
    }
    public SubFeatureInfo getSubFeature(String deviceName, Integer deviceNumber, Integer subFeatureValue) {
        var device = checkDeviceCache().get(deviceName);
        var feature = checkFeatureCache(device).get(deviceNumber);

        return checkSubFeatureCache(feature).get(subFeatureValue);
    }

    public List<DeviceInfo> getDevices() {
        return checkDeviceCache().values().stream().toList();
    }
    public List<FeatureInfo> getFeatures(String deviceName) {
        var device = checkDeviceCache().get(deviceName);

        return checkFeatureCache(device).values().stream().toList();
    }

    public List<SubFeatureInfo> getSubFeatures(String deviceName, Integer featureNumber) {
        var device = checkDeviceCache().get(deviceName);
        var feature = checkFeatureCache(device).get(featureNumber);

        return checkSubFeatureCache(feature).values().stream().toList();
    }

    public SensorData getValue(String deviceName, Integer featureNumber, Integer subFeatureNumber) {
        var device = checkDeviceCache().get(deviceName);
        var feature = checkFeatureCache(device).get(featureNumber);

        return monitor.getValue(checkSubFeatureCache(feature).get(subFeatureNumber));
    }

    private Map<String, DeviceInfo> checkDeviceCache() {
        if(deviceInfoMap.isEmpty()) {
            monitor.getDevices().forEach(deviceInfo -> deviceInfoMap.put(deviceInfo.getName(), deviceInfo));
        }
        return deviceInfoMap;
    }

    private Map<Integer, FeatureInfo> checkFeatureCache(DeviceInfo device) {
        return featureInfoMap.computeIfAbsent(device, unused -> monitor.getFeatures(device).stream().collect(Collectors.toMap(
                FeatureInfo::getNumber,
                UnaryOperator.identity())));
    }

    private Map<Integer, SubFeatureInfo> checkSubFeatureCache(FeatureInfo feature) {
        return subFeatureInfoMap.computeIfAbsent(feature, unused -> monitor.getSubFeatures(feature).stream().collect(Collectors.toMap(
                SubFeatureInfo::getNumber,
                UnaryOperator.identity())));
    }
}
