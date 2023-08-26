package org.example.sensemon.application.adapter.secondary;

import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;

import java.util.List;

public interface SensorMonitor extends AutoCloseable {
    List<DeviceInfo> getDevices();
    List<FeatureInfo> getFeatures(DeviceInfo device);
    List<SubFeatureInfo> getSubFeatures(FeatureInfo feature);

    SensorData getValue(SubFeatureInfo subFeature);
}
