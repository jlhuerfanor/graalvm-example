package org.example.sensemon.application.adapter.secondary.jni;


import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class JniSensorMonitor implements SensorMonitor, AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(JniSensorMonitor.class);

    static {
        log.info("Loading native code...");
        System.load("/home/jhuerfano/git/endava/graalvm/java-client/src/main/native/build/libjsensors.so");
    }

    private static boolean sensorsReady = false;
    private static native boolean sensorsInit();
    private static native void sensorsCleanup();
    private static native DeviceInfo[] getChipNames();
    private static native FeatureInfo[] getChipFeatures(DeviceInfo deviceInfo);

    private List<DeviceInfo> sensors;

    public JniSensorMonitor() throws IOException {
        sensorsReady = sensorsReady || sensorsInit();

        if(!sensorsReady) {
            throw new IOException("Sensors library initialization error");
        } else {
            log.info("Sensors initialized");
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
        sensorsCleanup();
        log.info("Sensors cleaned up");
    }
}
