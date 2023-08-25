package org.example.sensemon.application.adapter.secondary.nativ;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;

import java.util.List;

public class NativeSensorMonitor implements SensorMonitor {

    private List<DeviceInfo> deviceInfo;

    @Override
    public List<DeviceInfo> getDevices() {
        return null;
    }

    @Override
    public List<FeatureInfo> getFeatures(DeviceInfo device) {
        return null;
    }

    @Override
    public List<SubFeatureInfo> getSubFeatures(FeatureInfo feature) {
        return null;
    }

    @Override
    public SensorData getValue(SubFeatureInfo subFeature) {
        return null;
    }

    private record DevicePair(DeviceInfo deviceInfo, NativeSensorsInterface.ChipNamePointer chipName){ }
}
