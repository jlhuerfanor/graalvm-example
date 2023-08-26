package org.example.sensemon.application.adapter.secondary.graalvm;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.DeviceInfo;
import org.example.sensemon.application.model.FeatureInfo;
import org.example.sensemon.application.model.SensorData;
import org.example.sensemon.application.model.SubFeatureInfo;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.CDoublePointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.word.WordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.*;

public class NativeSensorMonitor implements SensorMonitor {
    private static final Logger log = LoggerFactory.getLogger(NativeSensorMonitor.class);

    private final List<DeviceInfo> deviceInfo = new LinkedList<>();
    private final Map<DeviceInfo, List<FeatureInfo>> features = new HashMap<>();
    private final Map<FeatureInfo, List<SubFeatureInfo>> subfeatures = new HashMap<>();
    private final boolean sensorsReady;

    public NativeSensorMonitor() {
        NativeSensorsInterface.FilePointer p = WordFactory.nullPointer();
        this.sensorsReady = NativeSensorsInterface.init(p) == 0;

        log.atLevel(this.sensorsReady ? Level.INFO : Level.ERROR).log("Sensors initialized");
    }

    @Override
    public List<DeviceInfo> getDevices() {
        if (sensorsReady) {
            if(deviceInfo.isEmpty()) {
                var nullChipName = WordFactory.<NativeSensorsInterface.ChipNameStruct>nullPointer();
                var numberPtr = StackValue.get(CIntPointer.class);

                NativeSensorsInterface.ChipNameStruct current;
                numberPtr.write(0);

                while ((current = NativeSensorsInterface.getDetectedChips(nullChipName, numberPtr)).isNonNull()) {
                    var device = NativeSensorsInterface.toDeviceInfo(numberPtr, current);

                    deviceInfo.add(device);
                }
            }
            return Collections.unmodifiableList(deviceInfo);
        } else return null;
    }

    @Override
    public List<FeatureInfo> getFeatures(DeviceInfo device) {
        if (sensorsReady) {
            var chipName = NativeSensorsInterface.getChipName(device.getSystemId());

            if(chipName.isNonNull()) {
                return features.computeIfAbsent(device, unused -> {
                    var features = new ArrayList<FeatureInfo>();
                    var numberPtr = StackValue.get(CIntPointer.class);

                    NativeSensorsInterface.FeatureStruct current;
                    numberPtr.write(0);

                    while ((current = NativeSensorsInterface.getFeatures(chipName, numberPtr)).isNonNull()) {
                        var feature = NativeSensorsInterface.toFeatureInfo(numberPtr, current);

                        features.add(feature.toBuilder()
                                .deviceInfo(device)
                                .build());
                    }

                    return features;
                });
            }
        }
        return null;
    }

    @Override
    public List<SubFeatureInfo> getSubFeatures(FeatureInfo feature) {
        if (sensorsReady) {
            var nativeChipName = NativeSensorsInterface.getChipName(feature.getDeviceInfo().getSystemId());
            var nativeFeature = NativeSensorsInterface.getFeature(nativeChipName, feature.getSystemId());

            if(nativeFeature.isNonNull()) {
                return subfeatures.computeIfAbsent(feature, unused -> {
                    var subFeatures = new ArrayList<SubFeatureInfo>();
                    var numberPtr = StackValue.get(CIntPointer.class);

                    NativeSensorsInterface.SubFeatureStruct current;
                    numberPtr.write(0);

                    while ((current = NativeSensorsInterface.getSubFeatures(nativeChipName, nativeFeature, numberPtr)).isNonNull()) {
                        var subFeature = NativeSensorsInterface.toSubFeatureInfo(numberPtr, current);

                        subFeatures.add(subFeature.toBuilder()
                                .featureInfo(feature)
                                .build());
                    }

                    return subFeatures;
                });
            }
        }
        return null;
    }

    @Override
    public SensorData getValue(SubFeatureInfo subFeature) {
        var nativeChipName = NativeSensorsInterface.getChipName(subFeature
                .getFeatureInfo()
                .getDeviceInfo()
                .getSystemId());

        var value = StackValue.get(CDoublePointer.class);
        var result = NativeSensorsInterface.getValue(nativeChipName, subFeature.getNumber(), value);

        return SensorData.builder()
                .failed(result != 0)
                .value(value.read())
                .build();
    }

    @Override
    public void close() {
        NativeSensorsInterface.cleanup();
        log.info("Cleaned up sensors");
    }


}
