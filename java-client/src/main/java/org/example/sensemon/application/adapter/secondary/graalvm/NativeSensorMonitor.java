package org.example.sensemon.application.adapter.secondary.graalvm;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.adapter.secondary.jni.JniSensorMonitor;
import org.example.sensemon.application.model.*;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

import java.util.*;

public class NativeSensorMonitor implements SensorMonitor {

    private List<DeviceInfo> deviceInfo = new LinkedList<>();
    private Map<String, List<FeatureInfo>> features = new HashMap<>();
    private Map<FeatureKey, List<SubFeatureInfo>> subfeatures = new HashMap<>();
    private boolean ready;

    public NativeSensorMonitor() {
        NativeSensorsInterface.FilePointer p = WordFactory.nullPointer();
        this.ready = NativeSensorsInterface.init(p) == 0;
    }

    @Override
    public List<DeviceInfo> getDevices() {
        if (ready) {
            if(deviceInfo.isEmpty()) {
                NativeSensorsInterface.ChipNamePointer current;
                CIntPointer number = StackValue.get(CIntPointer.class);

                number.write(0);

                while((current = NativeSensorsInterface.getDetectedChips(WordFactory.nullPointer(), number)).isNonNull()) {
                    var name = NativeSensorsInterface.getName(current);
                    var device = DeviceInfo.builder()
                            .name(name)
                            .bus(DeviceBus.builder()
                                    .type(BusType.fromCode(current.read().getBusId().getType()))
                                    .number(current.read().getBusId().getNumber())
                                    .build())
                            .prefix(CTypeConversion.toJavaString(current.read().getPrefix()))
                            .path(CTypeConversion.toJavaString(current.read().getPath()))
                            .address(current.read().getAddress())
                            .systemId(number.read() - 1)
                            .build();

                    System.out.println(device);
                    deviceInfo.add(device);
                }
            }
            return Collections.unmodifiableList(deviceInfo);
        } else return null;
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

    @Override
    public void close() {
        NativeSensorsInterface.cleanup();
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
