package org.example.sensemon.infrastructure;

import org.example.sensemon.adapter.SampleFilter;
import org.example.sensemon.adapter.SensorMonitor;
import org.example.sensemon.adapter.graalvm.NativeSensorMonitor;
import org.example.sensemon.adapter.graalvm.PythonSampleFilter;
import org.example.sensemon.adapter.jni.JniSensorMonitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SensorInterfaceConfiguration {
    private static final String INTERFACE_NATIVE = "NATIVE";
    private static final String INTERFACE_JNI = "JNI";

    private final String sensorsInterface;

    public SensorInterfaceConfiguration(
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

    @Bean
    public SampleFilter sampleFilter() throws IOException {
        if(INTERFACE_JNI.equals(sensorsInterface)) {
            return new PythonSampleFilter();
        } else if(INTERFACE_NATIVE.equals(sensorsInterface)) {
            return sample -> sample;
        } else throw new IllegalArgumentException(sensorsInterface);
    }
}
