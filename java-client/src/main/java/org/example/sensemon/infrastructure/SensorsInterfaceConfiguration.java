package org.example.sensemon.infrastructure;

import org.example.sensemon.application.adapter.secondary.SensorMonitor;
import org.example.sensemon.application.adapter.secondary.graalvm.NativeSensorMonitor;
import org.example.sensemon.application.adapter.secondary.jni.JniSensorMonitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensorsInterfaceConfiguration {
    private static final String INTERFACE_NATIVE = "NATIVE";
    private static final String INTERFACE_JNI = "JNI";

    private final String sensorsInterface;

    public SensorsInterfaceConfiguration(
            @Value("${application.sensor-interface}") String sensorsInterface) {
        this.sensorsInterface = sensorsInterface;
    }

    @Bean
    public SensorMonitor sensorMonitor() {
        if(INTERFACE_JNI.equals(sensorsInterface)) {
            return new JniSensorMonitor();
        } else if(INTERFACE_NATIVE.equals(sensorsInterface)) {
            return new NativeSensorMonitor();
        } else throw new IllegalArgumentException(sensorsInterface);
    }
}
